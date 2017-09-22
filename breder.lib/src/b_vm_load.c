#include "breder.h"

b_method_id_t b_vm_load (b_vm_t* self, const char* filename) {
	return b_vm_load1(self, filename, -1);
}

b_method_id_t b_vm_load1 (b_vm_t* self, const char* filename, unsigned int fsize) {
	b_method_id_t method_start;
	void* file = b_file_open_read((void*)filename);
	if (!file) {
		b_log_public_print_error("Can not found '%s' file", filename);
		return 0;
	}
	b_file_set_size(file, fsize);
	/**
	 * Reconhecendo o arquivo binário com o cabeçalho inicial.
	 * Seu formato é : BB
	 */
	{
		if (! (b_file_read(file) == 'B' && b_file_read(file) == 'B')) {
			b_log_public_print_error("Not a Breder binary '%s'.\n", filename);
			goto end;
		}
		b_file_read(file);
	}
	/**
	 * Bloco que lê o nome da classe inicializador.
	 * Seu formato é : (%d,%s)
	 */
	{
		method_start = (b_method_id_t)b_file_read_int(file);
	}
	/**
	 * Bloco que lê todas as bibliotecas nativas para a vm.
	 */
	{
		int n, nsize = b_file_read_int(file);
		int m, msize = b_array_size_safely(self->libraryPaths);
		for (n = 0; n < nsize; n++) {
			char* libraryname = b_file_read_chars(file);
			void* library = 0;
			for (m = 0; m < msize; m++) {
				b_char_t* librarypath = b_array_get_typed(b_char_t, self->libraryPaths, m);
				if ( (library = b_so_dynamic_load(librarypath, libraryname))) {
					b_array_add(self->libraryFuncs, library);
					break;
				}
			}
			if (!library) {
				printf("Cannot find the library '%s' in %d path(s) : \n", libraryname, msize);
				for (m = 0; m < msize; m++) {
					b_char_t* dir = b_array_get_typed(b_char_t, self->libraryPaths, m);
					char* path = b_char_new4(dir, SO_FILE_SEPARATOR, libraryname, SO_EXTENSION);
					printf("\t%s\n", path);
				}
				printf("\nPlease, use the option of Breder Virtual Machine '-lp' to specify the library path.\n");
				exit(0);
			}
		}
	}
	/**
	 * Bloco que lê o nome de todas as classes
	 * Seu formato é : {(%d,%s)*}
	 */
	{
		int eindex, cindex, csize = self->classSize = b_file_read_int(file);
		self->classes = b_memory_alloc_typed(b_class_t*, csize);
		for (cindex = 0; cindex < csize; cindex++) {
			b_class_t* clazz = b_memory_alloc_struct(b_class_t);
			clazz->name = b_file_read_chars(file);
			clazz->index = cindex;
			clazz->extendindex = b_file_read_aint(file);
			clazz->implementindex = b_file_read_aint(file);
			clazz->castindex = b_aint_new(b_aint_size(clazz->extendindex) + b_aint_size(clazz->implementindex));
			for (eindex = 0; eindex < b_aint_size(clazz->extendindex); eindex++) {
				b_aint_set(clazz->castindex, eindex, b_aint_get(clazz->extendindex, eindex));
			}
			for (eindex = 0; eindex < b_aint_size(clazz->implementindex); eindex++) {
				b_aint_set(clazz->castindex, eindex + b_aint_size(clazz->extendindex), b_aint_get(clazz->implementindex, eindex));
			}
			self->classes[cindex] = clazz;
		}
	}
	/**
	 * Bloco que lê todos os campos das classes
	 * Seu formato é :
	 */
	{
		int findex, cindex, sizen, sizef, fields = 0;
		sizen = self->classSize;
		for (cindex = 0; cindex < sizen; cindex++) {
			b_class_t* clazz = b_class_get(self, cindex);
			b_file_scan1(file, "%d,", &sizef);
			clazz->fields = b_array_new1(sizef);
			for (findex = 0; findex < sizef; findex++) {
				b_field_t* field = b_memory_alloc_struct(b_field_t);
				b_file_scan1(file, "%d,", &field->isStatic);
				field->cindex = cindex;
				field->name = b_file_read_string(file);
				b_array_add(self->fields, field);
				if (!field->isStatic) {
					field->index = fields++;
					b_array_add(clazz->fields, field);
				}
			}
		}
	}
	{
		self->ofields = b_memory_alloc_typed(b_object_t*, b_array_size_safely(self->fields));
	}
	/**
	 * Bloco que lê todos os metodos das classes
	 * Seu formato é :
	 */
	{
		int cindex, sizen = self->classSize;
		for (cindex = 0; cindex < sizen; cindex++) {
			b_class_t* clazz = b_class_get(self, cindex);
			int mindex, msize;
			b_file_scan1(file, "%d,", &msize);
			clazz->methods = b_array_new1(msize);
			for (mindex = 0; mindex < msize; mindex++) {
				char* name = b_file_read_string(file);
				int* values = b_file_read_ints(file, 0);
				b_method_t* method = b_memory_alloc_struct(b_method_t);
				method->aname = b_char_new4("_", clazz->name, "_", name);
				method->name = name;
				method->cindex = cindex;
				method->index = b_array_size_safely(self->methods);
				method->isStatic = values[1];
				method->isConstructor = values[3];
				method->params = b_file_read_aints(file);
				method->memIndex = values[4];
				method->returns = values[0];
				method->isAbstract = values[2] != 0;
				b_array_add(self->methods, method);
				b_array_add(clazz->methods, method);
				b_memory_free(values);
			}
		}
	}
	/**
	 * Bloco que lê todos os nomes nativos para as funcões nativas
	 * Seu formato é : {(%d,%d,%s)*}
	 */
	{
		int n, nsize;
		b_boolean_t ok = 1;
		b_file_scan1(file, "%d,", &nsize);
		for (n = 0; n < nsize; n++) {
			int* ints = b_file_read_ints(file, 0);
			char* name = b_file_read_string(file);
			int index = ints[0];
			b_method_t* method = b_method_get(self, index);
			{
				int n, size = b_array_size(self->libraryFuncs);
				for (n = 0; n < size; n++) {
					void* library = b_array_get_typed(void, self->libraryFuncs, n);
					void* func = b_so_dynamic_get(library, name);
					if (func) {
						method->isNative = func;
						break;
					}
				}
				if (!method->isNative) {
					if (ok) {
						b_log_public_print_error("not found native method :");
					}
					if (method->isStatic) {
						b_log_public_print("b_bni_state_t %s (b_vm_t* vm) {}\n", name);
					} else {
						b_log_public_print("b_bni_state_t %s (b_vm_t* vm, b_object_t* object) {}\n", name);
					}
					ok = 0;
				}
			}
			b_memory_free(ints);
		}
		if (!ok) {
			exit(0);
		}
	}
	{
		int n, sizec = self->classSize;
		self->dataIndexs = b_memory_alloc_typed(int*, sizec);
		for (n = 0; n < sizec; n++) {
			self->dataIndexs[n] = b_memory_alloc_typed(int, sizec);
		}
		self->dataIndexCount = b_memory_alloc_typed(int, sizec);
		for (n = 0; n < sizec; n++) {
			b_class_t* clazz = b_class_get(self, n);
			int index = 0, fields = 0;
			b_vm_init_cache_linearing(self, &index, &fields, clazz, clazz);
			self->dataIndexCount[n] = index;
			clazz->fieldCount = fields;
		}
	}
	{
		int n, sizec = self->classSize;
		if (! (self->fvirtual = b_memory_alloc_typed(b_field_id_t*, sizec))) {
			return B_BNI_FAIL;
		}
		int sizef = b_array_size_safely(self->fields);
		for (n = 0; n < sizec; n++) {
			if (! (self->fvirtual[n] = b_memory_alloc_typed(b_method_id_t, sizef))) {
				return B_BNI_FAIL;
			}
		}
		for (n = 0; n < sizec; n++) {
			b_class_t* clazz = b_class_get(self, n);
			int fields = 0;
			b_vm_init_cache_field_aux1(self, clazz, clazz, &fields);
			clazz->fieldCount = fields;
		}
	}
	{
		int n, sizec = self->classSize;
		self->mvirtual = b_memory_alloc_typed(b_method_id_t*, sizec);
		int sizem = b_array_size_safely(self->methods);
		for (n = 0; n < sizec; n++) {
			self->mvirtual[n] = b_memory_alloc_typed(b_method_id_t, sizem);
		}
		for (n = 0; n < sizec; n++) {
			b_class_t* aux_clazz = b_class_get(self, n);
			b_vm_init_cache_method_aux(self, aux_clazz, aux_clazz);
		}
	}
	{
		int cindex1, cindex2, sizec = self->classSize;
		if (! (self->cast = b_memory_alloc_typed(b_state_t*, sizec))) {
			return B_BNI_FAIL;
		}
		for (cindex1 = 0; cindex1 < sizec; cindex1++) {
			if (! (self->cast[cindex1] = b_memory_alloc_typed(b_state_t, sizec))) {
				return B_BNI_FAIL;
			}
			b_class_t* c1 = b_class_get(self, cindex1);
			for (cindex2 = 0; cindex2 < sizec; cindex2++) {
				self->cast[cindex1][cindex2] = b_vm_opcode_cast_aux(self, cindex2, c1);
			}
		}
	}
	{
		self->ointegers = b_memory_alloc_typed(b_object_t*, B_NUMBER_POOL_MAX * 2 + 1);
		self->obooleans = b_memory_alloc_typed(b_object_t*, 2);
		self->obooleans[0] = b_object_new_boolean_cached(self, 0);
		self->obooleans[1] = b_object_new_boolean_cached(self, 1);
	}
	{
		self->index_id = b_bni_class_index_id(self);
		self->natural_id = b_bni_class_natural_id(self);
		self->integer_id = b_bni_class_integer_id(self);
		self->number_id = b_bni_class_number_id(self);
		self->boolean_id = b_bni_class_boolean_id(self);
		self->string_id = b_bni_class_string_id(self);
	}
	/**
	 * Bloco que lê todas as strings estáticas da vm.
	 * Seu formato é : {(%d,%s)*}
	 */
	{
		int n, size;
		b_file_scan1(file, "%d,", &size);
		for (n = 0; n < size; n++) {
			char* name = b_file_read_string(file);
			unsigned int hash = b_char_hash_build(name);
			unsigned int len = b_char_size(name);
			b_object_t* object = b_object_new_string_generic(self, name, len, hash, 1);
			self->ostrings = b_memory_realloc_typed(b_object_t*, self->ostrings, (self->ostringsLen + 1));
			self->ostrings[self->ostringsLen++] = object;
			b_memory_free(name);
		}
	}
	/**
	 * Bloco que lê todas os números estáticos da vm.
	 * Seu formato é : {(%d,%s)*}
	 */
	{
		int n, size;
		b_file_scan1(file, "%d,", &size);
		for (n = 0; n < size; n++) {
			char* name = b_file_read_string(file);
			{
				b_number_t number;
				//b_number_t number = b_number_scanf(name);
				sscanf(name, B_NUMBER_SCANF, &number);
				b_object_t* object;
				if (number == (int)number) {
					if (number > 0) {
						object = b_object_new_number_generic(self, b_bni_class_index_id(self), number, 1);
					} else if (number == 0) {
						object = b_object_new_number_generic(self, b_bni_class_natural_id(self), number, 1);
					} else {
						object = b_object_new_number_generic(self, b_bni_class_integer_id(self), number, 1);
					}
				} else {
					object = b_object_new_number_generic(self, b_bni_class_number_id(self), number, 1);
				}
				self->onumbers = b_memory_realloc_typed(b_object_t*, self->onumbers, (self->onumbersLen + 1));
				self->onumbers[self->onumbersLen++] = object;
			}
			b_memory_free(name);
		}
	}
	/**
	 * Bloco que lê todas as instruções
	 */
	{
		for (;;) {
			int i1, i2, i3, i4;
			__inst inst = 0;
			{
				inst += (i1 = b_file_read(file)) << 24;
				inst += (i2 = b_file_read(file)) << 16;
				inst += (i3 = b_file_read(file)) << 8;
				inst += (i4 = b_file_read(file)) << 0;
			}
			{
				if (i1 == -1 && i2 == -1 && i3 == -1 && i4 == -1) break;
				if (i1 == -1 || i2 == -1 || i3 == -1 || i4 == -1) {
					if (b_file_eof(file)) break;
					printf("read error : %d\n", b_file_error(file));
					b_so_exit(3);
				}
			}
#ifdef _DEBUG_
			if (false) {
				(i1 = b_file_read (file)) << 24;
				(i2 = b_file_read (file)) << 16;
				(i3 = b_file_read (file)) << 8;
				(i4 = b_file_read (file)) << 0;
			}
			if (false) {
				if (i1 == - 1 || i2 == - 1 || i3 == - 1 || i4 == - 1) {
					printf ("read error : %d\n", b_file_error (file));
					b_so_exit (3);
				}
			}
			short cindex = 0;
			if (false) {
				cindex += (i1 = b_file_read (file)) << 8;
				cindex += (i2 = b_file_read (file)) << 0;
			}
			short line = 0;
			if (false) {
				line += (i3 = b_file_read (file)) << 8;
				line += (i4 = b_file_read (file)) << 0;
			}
			if (false) {
				if (i1 == - 1 || i2 == - 1 || i3 == - 1 || i4 == - 1) {
					printf ("read error : %d\n", b_file_error (file));
					b_so_exit (3);
				}
			}
#endif
			{
				if (self->bufferSize == self->bufferAlloced) {
					self->bufferAlloced *= 2;
					self->progmem = b_memory_realloc_typed(__inst, self->progmem, self->bufferAlloced);
					// self->pc_cindex = b_memory_realloc_typed (b_class_id_t, self->pc_cindex, self->bufferAlloced);
					// self->pc_line = b_memory_realloc_typed (short, self->pc_line, self->bufferAlloced);
				}
				self->progmem[self->bufferSize] = inst;
				// self->pc_cindex[self->bufferSize] = cindex;
				// self->pc_line[self->bufferSize] = line;
				self->bufferSize++;
			}
		}
		{
			self->bufferAlloced = self->bufferSize;
			self->progmem = b_memory_realloc_typed(__inst, self->progmem, self->bufferAlloced);
			// self->pc_cindex = b_memory_realloc_typed (b_class_id_t, self->pc_cindex, self->bufferAlloced);
			// self->pc_line = b_memory_realloc_typed(short, self->pc_line, self->bufferAlloced * sizeof(short));
		}
	}
	b_file_close(file);
	return method_start;
	end : {
		if (file) {
			b_file_close(file);
		}
		b_error_put("[error] : error in the binary file\n");
		return 0;
	}
}

// Método que faz a leitura de arquivo na syntax : '(' ( %d ( ',' %d )* )? ')'
b_ints_t* b_vm_read_ints (void* f) {
	char c = b_file_read(f);
	if (c != '(') {
		return 0;
	}
	b_array_t* array = b_array_new();
	if (array == null) {
		return null;
	}
	while (true) {
		int size, state = b_file_scan1(f, "%d", &size);
		if (!state || state == EOF) {
			c = b_file_read(f);
			if (c == ')') {
				break;
			} else {
				b_array_free1(array, (void*)b_memory_free);
				return 0;
			}
		} else {
			int* pint = b_memory_alloc_struct(int);
			*pint = size;
			b_array_add(array, pint);
			c = b_file_read(f);
			if (c == ',') {
				continue;
			} else if (c == ')') {
				break;
			} else {
				b_array_free1(array, (void*)b_memory_free);
				return 0;
			}
		}
	}
	int size = b_array_size(array);
	b_ints_t* data = b_memory_alloc_typed (b_ints_t, size + 1);
	int* result = data;
	*result++ = size;
	int n;
	for (n = 0; n < size; n++) {
		*result++ = *b_array_get_typed(int, array, n);
	}
	return data;
}

b_state_t b_vm_opcode_cast_aux (b_vm_t* vm, unsigned int index, b_class_t* clazz) {
	if (index == clazz->index) {
		return B_STATE_SUCCESS;
	}
	register int n, size = b_aint_size(clazz->castindex);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_class_get(vm, b_aint_get(clazz->castindex, n));
		if (extend->index == index) {
			return B_STATE_SUCCESS;
		}
		if (b_vm_opcode_cast_aux(vm, index, extend) == B_STATE_SUCCESS) {
			return B_STATE_SUCCESS;
		}
	}
	return B_STATE_FAIL;
}

void b_vm_init_cache_method_aux_aux (b_vm_t* vm, b_class_t* bclass, b_class_t* clazz, b_method_t* method) {
	int n, sizen = b_aint_size(clazz->castindex);
	for (n = sizen - 1; n >= 0; n--) {
		b_class_t* extend = b_class_get_extend (vm, clazz, n);
		b_vm_init_cache_method_aux_aux(vm, bclass, extend, method);
	}
	int m, sizem = b_array_size(clazz->methods);
	for (m = 0; m < sizem; m++) {
		b_method_t* smethod = b_method_get_byclass (vm, clazz, m);
		if (!b_char_compare(smethod->name, method->name)) {
			vm->mvirtual[bclass->index][smethod->index] = method->index;
			break;
		}
	}
}

void b_vm_init_cache_method_aux (b_vm_t* vm, b_class_t* bclass, b_class_t* clazz) {
	int n, sizen = b_aint_size(clazz->castindex);
	for (n = sizen - 1; n >= 0; n--) {
		b_class_t* extend = b_class_get_extend (vm, clazz, n);
		b_vm_init_cache_method_aux(vm, bclass, extend);
	}
	int m, sizem = b_array_size(clazz->methods);
	for (m = 0; m < sizem; m++) {
		b_method_t* method = b_array_get_typed(b_method_t, clazz->methods, m);
		b_vm_init_cache_method_aux_aux(vm, bclass, clazz, method);
	}
}

void b_vm_init_cache_field_aux1 (b_vm_t* vm, b_class_t* bclass, b_class_t* clazz, int* fields) {
	int n, m, size = b_aint_size(clazz->castindex);
	for (n = size - 1; n >= 0; n--) {
		b_class_t* extend = b_class_get_extend (vm, clazz, n);
		b_vm_init_cache_field_aux1(vm, bclass, extend, fields);
	}
	int sizem = b_array_size(clazz->fields);
	for (m = 0; m < sizem; m++) {
		b_field_t* field = b_array_get_typed(b_field_t, clazz->fields, m);
		vm->fvirtual[bclass->index][field->index] = *fields;
		*fields += 1;
	}
}

void b_vm_init_cache_linearing (b_vm_t* vm, int* index, int* fields, b_class_t* bclass, b_class_t* clazz) {
	vm->dataIndexs[bclass->index][clazz->index] = *index;
	*fields += b_array_size_safely(clazz->fields);
	*index += 1;
	int n, size = b_aint_size(clazz->extendindex);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_class_get(vm, b_aint_get(clazz->extendindex, n));
		b_vm_init_cache_linearing(vm, index, fields, bclass, extend);
	}
}
