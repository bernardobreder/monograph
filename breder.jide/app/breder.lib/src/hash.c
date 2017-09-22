#include "breder.h"

b_hashnew_t* b_hash_new2() {
	return b_hash_new(sizeof(void*), null);
}

b_hashnew_t* b_hash_new(int size, void* freeFunc) {
	return b_hash_new1(size, freeFunc, true);
}

b_hashnew_t* b_hash_new1(int size, void* freeFunc, int isDupKey) {
	b_hashnew_t* self = calloc(1, sizeof(b_hashnew_t));
	self->isDupKey = isDupKey;
	self->entitys = b_arrayp_new ( );
	int n;
	for (n = 0; n < B_HASH_SIZE; n++) {
		b_arrayp_add(self->entitys,0);
	}
	return self;
}

static b_hashentity_t* b_hashnew_entity_new(b_char_t* chars, void* value) {
	b_hashentity_t* self = calloc(1, sizeof(b_hashentity_t));
	self->key = chars;
	self->size = strlen(chars);
	self->hash = b_char_build_hash_t(chars);
	self->value = value;
	return self;
}

void* b_hashnew_put2(b_hashnew_t* self, b_chars_t* key, void* value) {
	return b_hashnew_put(self, key->chars, b_chars_hash(key),
			b_chars_size(key), value);
}

void* b_hashnew_put1(b_hashnew_t* self, const char* key, void* value) {
	int hash = b_char_build_hash_t(key);
	int size = b_char_size(key);
	return b_hashnew_put(self, key, hash, size, value);
}

void* b_hashnew_put(b_hashnew_t* self, const char* key, int hash, int len,
		void* value) {
	int select = hash % b_array_size(self->entitys);
	b_arrayp_t* array =
			b_arrayp_get_typed( b_arrayp_t , self->entitys , select );
	if (!array) {
		array = b_arrayp_new ( );
		b_arrayp_set( self->entitys , select , array );
		char* k = self->isDupKey ? b_char_dup(key) : key;
		b_arrayp_add( array , b_hashnew_entity_new(k, value));
		self->size++;
		return null;
	} else {
		b_hashentity_t** entitys = (b_hashentity_t**) array->array;
		int n;
		int size = b_array_size( array );
		for (n = 0; n < size; n++) {
			b_hashentity_t* entity = *entitys++;
			if (entity->hash == hash && entity->size == len && !strcmp(
					entity->key, key)) {
				if (self->freeFunc)
					b_call_function( void , self->freeFunc , (void*) )(
							entity->value);
				void* result = entity->value;
				entity->value = value;
				return result;
			}
		}
		if (n == size) {
			char* k = self->isDupKey ? b_char_dup(key) : key;
			b_arrayp_add( array , b_hashnew_entity_new(k, value) );
			self->size++;
			return null;
		}
	}
}

int b_hashnew_size(b_hashnew_t* self) {
	return self->size;
}

void* b_hashnew_get1(b_hashnew_t* self, char* key) {
	int hash = b_char_build_hash_t(key);
	void* data = b_hashnew_get(self, key, hash);
	return data;
}

void* b_hashnew_get(b_hashnew_t* self, b_char_t* key, int hash) {
	int select = hash % b_array_size(self->entitys);
	b_arrayp_t* array =
			b_arrayp_get_typed( b_arrayp_t , self->entitys , select );
	if (array) {
		b_hashentity_t** entitys = (b_hashentity_t**) array->array;
		int n;
		int size = b_array_size( array );
		for (n = 0; n < size; n++) {
			b_hashentity_t* entity = *entitys++;
			if (entity->hash == hash && !strcmp(entity->key, key)) {
				return entity->value;
			}
		}
	}
	return 0;
}

void* b_hashnew_get2(b_hashnew_t* self, b_chars_t* key) {
	int select = b_chars_hash(key) % b_array_size(self->entitys);
	int len = b_chars_size(key);
	int hash = b_chars_hash(key);
	b_arrayp_t* array =
			b_arrayp_get_typed( b_arrayp_t , self->entitys , select );
	if (array) {
		b_hashentity_t** entitys = (b_hashentity_t**) array->array;
		int n, size = b_array_size( array );
		for (n = 0; n < size; n++) {
			b_hashentity_t* e = *entitys++;
			if (e->hash == hash && e->size == len
					&& !strcmp(e->key, key->chars)) {
				return e->value;
			}
		}
	}
	return 0;
}

void b_hashnew_free1(b_hashnew_t* self, void* func) {
	int n;
	int size = b_array_size(self->entitys);
	for (n = 0; n < size; n++) {
		b_arrayp_t* array = b_arrayp_get( self->entitys , n );
		if (array) {
			b_hashentity_t** entitys = (b_hashentity_t**) array->array;
			int n;
			int size = b_array_size( array );
			for (n = 0; n < size; n++) {
				b_hashentity_t* entity = *entitys++;
				if (self->isDupKey) {
					b_char_free(entity->key);
				}
				if (func)
					b_call_function(void,func,(void*))(entity->value);
				free(entity);
			}
			b_array_free(array);
		}
	}
	b_array_free(self->entitys);
	free(self);
}

void b_hashnew_free(b_hashnew_t* self) {
	return b_hashnew_free1(self, self->freeFunc);
}

void** b_hashnew_toarray(b_hashnew_t* self) {
	void** datas = calloc(self->size, sizeof(void*));
	int n, m, p = 0;
	int size1 = b_array_size(self->entitys);
	for (n = 0; n < size1; n++) {
		b_arrayp_t* array = b_arrayp_get( self->entitys , n );
		if (array) {
			b_hashentity_t** entitys = (b_hashentity_t**) array->array;
			int size2 = b_array_size( array );
			for (m = 0; m < size2; m++) {
				b_hashentity_t* entity = *entitys++;
				datas[p++] = entity->value;
			}
		}
	}
	return datas;
}

int b_hashp_get_index(b_hashnew_t* self, char* key) {
	unsigned int hash = b_char_build_hash_t(key);
	int n, m, p = 0;
	int size1 = b_array_size(self->entitys);
	for (n = 0; n < size1; n++) {
		b_arrayp_t* array = b_arrayp_get( self->entitys , n );
		if (array) {
			b_hashentity_t** entitys = (b_hashentity_t**) array->array;
			int size2 = b_array_size( array );
			for (m = 0; m < size2; m++) {
				b_hashentity_t* entity = *entitys++;
				if (entity->hash == hash && !strcmp(entity->key, key))
					return p;
				else
					p++;
			}
		}
	}
	return -1;
}

void** b_hashnew_close(b_hashnew_t* self) {
	int n, m, size = b_array_size(self->entitys);
	int len = 0;
	for (n = 0; n < size; n++) {
		b_arrayp_t* array = b_arrayp_get( self->entitys , n );
		if (b_array_size(array) > len) {
			len = b_array_size(array);
		}
	}
	void** data = malloc((2 * sizeof(int)) + (len * size
			* sizeof(b_hashentity_t)));
	int* header = (int*) data;
	*header++ = size;
	*header++ = len;
	b_hashentity_t* content = (b_hashentity_t*) header;
	b_hashentity_t entity;
	entity.hash = 0;
	entity.size = 0;
	entity.key = 0;
	entity.value = 0;
	for (n = 0; n < size; n++) {
		b_arrayp_t* array = b_arrayp_get( self->entitys , n );
		for (m = 0; m < len; m++) {
			if (b_array_size(array) > m) {
				b_hashentity_t* e = b_arrayp_get(array,m);
				content->hash = e->hash;
				content->size = e->size;
				if (!e->key) {
					e->key = 0;
				}
				if (self->isDupKey) {
					content->key = b_char_dup(content->key);
				} else {
					content->key = e->key;
				}
				content->value = e->value;
			} else {
				memcpy(content,&entity,sizeof(b_hashentity_t));
			}
			content++;
		}
	}
	return data;
}

void* b_hashclose_get(b_hashclose_t* self, const char* key) {
	int hash = b_char_build_hash_t(key);
	int len = b_char_size(key);
	b_hashentity_t* entitys = b_hashclose_begin(self,hash);
	int n, size = b_hashclose_entity_length(self);
	for (n = 0; n < size; n++) {
		if (entitys->hash == hash && entitys->size == len && !strcmp(
				entitys->key, key)) {
			return entitys->value;
		}
		entitys++;
	}
	return null;
}

void* b_hashclose_get1(b_hashclose_t* self, b_chars_t* ckey) {
	int hash = b_chars_hash(ckey);
	int len = b_chars_size(ckey);
	char* key = b_chars_text(ckey);
	b_hashentity_t* entitys = b_hashclose_begin(self,hash);
	int n, size = b_hashclose_entity_length(self);
	for (n = 0; n < size; n++) {
		if (entitys->hash == hash && entitys->size == len && !strcmp(
				entitys->key, key)) {
			return entitys->value;
		}
		entitys++;
	}
	return null;
}

void* b_hashclose_put1(b_hashclose_t* self, b_chars_t* ckey, void* value) {
	int hash = b_chars_hash(ckey);
	int len = b_chars_size(ckey);
	char* key = b_chars_text(ckey);
	b_hashentity_t* entitys = b_hashclose_begin(self,hash);
	int n, size = b_hashclose_entity_length(self);
	for (n = 0; n < size; n++) {
		if (entitys->hash == hash && entitys->size == len && !b_char_compare(
				entitys->key, key)) {
			void* result = entitys->value;
			entitys->value = value;
			return result;
		}
		entitys++;
	}
	return null;
}

void b_hashclose_free(b_hashclose_t* self) {
	free(self);
}

void b_hash_test() {
	b_hashnew_t* h = b_hashp_new ( 0 );
	int n;
	b_char_t* chars = calloc(101, sizeof(char));
	srand(2);
	{
		for (n = 0; n < 10000; n++) {
			int m;
			for (m = 0; m < 100; m++) {
				chars[m] = rand();
			}
			b_hashnew_put1(h, chars, n);
			assert(b_hashnew_get1(h,chars)==n);
		}
	}
	b_hashnew_put1(h, b_char_dup("Bernardo"), "Breder");
	void** hc = b_hashnew_close(h);
	assert(!strcmp(b_hashclose_get(hc, "Bernardo"),"Breder"));
	printf("Ok...\n");
	free(chars);
	b_hashnew_free(h);
	exit(0);
}

