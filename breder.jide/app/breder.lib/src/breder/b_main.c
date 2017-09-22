#include "breder.h"

int b_vm_main(int argc, char* argv[]) {
	int n = 1;
	char* classname;
	b_vm_t* vm = b_vm_new();
	if (argc > 1 && argv[n][0] != '-') {
#ifdef _DEBUG_
		printf("[info] : Another Output Binary File is include '%s'\n",argv[n]);
#endif
		b_vm_set_output(vm, argv[n++]);
	}
	{
		for (; n < argc; n++) {
			if ((!strcmp(argv[n], "-lp") || !strcmp(argv[n], "-linkpath")) && n
					!= argc - 1) {
#ifdef _DEBUG_
				printf("[info] : Another LinkerPath is include '%s'\n",argv[n+1]);
#endif
				b_vm_add_linkerpath(vm, argv[n + 1]);
				n++;
			}
		}
	}
	{
		int n = 1, found = false;
		for (; n < argc; n++) {
			if (!strcmp(argv[n], "-notnat")) {
				found = true;
			}
		}
		if (!found) {
			char* brederhome = getenv("BREDER_HOME");
#ifdef _DEBUG_
			printf("[info] : Environment Variable BREDER_HOME is found like '%s'\n",brederhome);
#endif
			if (brederhome) {
				b_vm_add_linkerpath(vm, b_char_new2(brederhome, "/nat"));
			}
		}
	}
	{
		classname = b_vm_load(vm, vm->output);
		if (!classname) {
			b_error_show();
			goto end;
		}
	}
	{
		int index = b_vm_execute(vm, classname, "() main ()");
		if (!index) {
			b_error_show();
		} else {
			int state = b_vm_opcode_sjump(vm, index);
			if (state == B_BNI_FAIL) {
				b_throw_print(vm, vm->othrow);
			}
		}
	}
	end: {
		b_vm_free(vm);
		return 0;
	}
}

