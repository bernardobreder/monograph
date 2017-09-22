#include "breder.h"

static int b_vm_main_print () {
	printf ("\n");
	printf ("Ex:\tbreder\n");
	printf ("\tbreder <filename>\n");
	printf ("\tbreder <filename> <option> ...\n");
	printf ("\tbreder <filename> <option> ... <argument> ...\n");
	printf ("\n");
	printf ("where options include:\n");
	printf ("\t-lp <pathname>\n");
	printf ("\t\tSpecify where to find the dynamic library\n");
	printf ("\t-no_breder_home\n");
	printf ("\t\tIndicate that will not consider the BREDER_HOME\n");
	printf ("where options for debug:\n");
	printf ("\t-debug_memory\n");
	printf ("\t\tIndicate how much memory you device has\n");
	printf ("\n");
	return 0;
}

static int b_vm_main_memory (int argc, const char* argv[]) {
	int n;
	b_memory_init ();
	for (n = 0; n < argc; n ++ ) {
		if ( ! b_char_compare(argv[n], "-debug_memory")) {
			if (n == argc - 1) {
				return b_vm_main_print ();
			} else {
				long long memory;
				if ( ! sscanf (argv[ ++ n], "%ld", & memory)) {
					return b_vm_main_print ();
				}
				b_memory_sized (memory);
			}
		}
	}
	return B_STATE_SUCCESS;
}

int b_vm_main (int argc, const char* argv[]) {
	int n = 0;
	b_vm_t* vm;
	b_assert_end(b_vm_main_memory (argc, argv));
	b_assert_outofmemory(vm = b_vm_new ());
	int no_breder_home = false;
	const char* output = "./binary.b";
	if (argc > 1) {
		if (argv[1][0] == '-') {
			return b_vm_main_print ();
		} else {
			output = argv[1];
		}
		for (n = 2; n < argc && argv[n][0] == '-'; n ++ ) {
			const char* att = argv[n];
			if ( ! b_char_compare(att, "-lp")) {
				if (n == argc - 1) {
					return b_vm_main_print ();
				} else {
					b_array_add (vm->libraryPaths, b_char_dup (argv[n + 1]));
					n ++ ;
				}
			} else if ( ! b_char_compare(att, "-no_breder_home")) {
				no_breder_home = true;
			}
		}
	}
	if ( ! no_breder_home) {
		char* brederhome = getenv ("BREDER_HOME");
		if (brederhome) {
			char* name = b_char_new2 (brederhome, "/nat");
			b_array_add (vm->libraryPaths, name);
		}
	}
	if ( ! b_so_file_exist (output)) {
		return b_vm_main_print ();
	}
	b_method_id_t index = b_vm_load (vm, output);
#ifdef DEBUG_APP
	vm->debug = b_debug_new();
	printf("Initializing bdb ...\n");
	while(!b_debug_not_running(vm)) {
		b_debug_ask(vm);
	}
#endif
	if (b_error_has ()) {
		b_error_show ();
	} else {
		b_bni_new_0_param(vm, olist, b_bni_method_arraylist_init_id(vm));
		for (; n < argc; n ++ ) {
			const char* arg = argv[n];
			b_bni_new_ostring_define(vm, oarg, arg);
			b_bni_execute_0_return_1_param(vm, olist, b_bni_method_ilist_add_id(vm), oarg);
		}
		b_bni_opcode_push(vm, olist);
		int state = b_bni_opcode_sjump (vm, index);
		if (state == B_BNI_FAIL) {
			b_vm_throw_print (vm);
		} else {
			b_gc_start (vm);
		}
	}
	b_memory_close ();
	end : {
		// b_vm_free (vm);
		return EXIT_SUCCESS;
	}
}
