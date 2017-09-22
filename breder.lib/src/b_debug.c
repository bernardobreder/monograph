#ifdef DEBUG_APP

#include "breder.h"

b_debug_t* b_debug_new() {
	b_debug_t* data = b_memory_alloc0(sizeof(b_debug_t));
	return data;
}

b_boolean_t b_debug_not_running(b_vm_t* vm) {
	return vm->debug->running;
}

char** b_debug_ask_line(int* size) {
	char* line = b_memory_alloc0(8 * 1024 * sizeof(char));
	{
		int index = 0;
		while(index == 0) {
			printf("> ");
			int canSpace = 0;
			int c = getchar();
			while(c != '\n') {
				if ((c > 32 && c < 127) || (c == ' ' && canSpace)) {
					if(c == ' ') {
						canSpace = 0;
					} else {
						canSpace = 1;
					}
					line[index++] = c;
				}
				c = getchar();
			}
		}
		if(line[strlen(line)-1] == ' ') {
			line[strlen(line)-1] = 0;
		}
	}
	*size = 1;
	{
		char* aux = line;
		char* index = strchr(aux, ' ');
		while(index) {
			*size += 1;
			aux = index + 1;
			if(*aux == 0) {
				break;
			}
			index = strchr(aux, ' ');
		}
	}
	char** charss = b_memory_alloc0(*size * sizeof(char*));
	{
		int i = 0;
		char* aux = line;
		char* index = strchr(aux, ' ');
		while(index) {
			*index = 0;
			char* chars = b_memory_alloc0((strlen(aux) + 1) * sizeof(char));
			strcpy(chars, aux);
			charss[i++] = chars;
			*index = ' ';
			aux = index + 1;
			index = strchr(aux, ' ');
		}
		{
			char* chars = b_memory_alloc0((strlen(aux) + 1) * sizeof(char));
			strcpy(chars, aux);
			charss[i++] = chars;
		}
	}
	return charss;
}

void b_debug_ask(b_vm_t* vm) {
	int argc;
	char** cmds = b_debug_ask_line(&argc);
	if(!strcmp(*cmds, "run")) {
		vm->debug->running = true;
	} else if(!strcmp(*cmds, "quit")) {
		exit(0);
	} else if(!strcmp(*cmds, "?")) {
		b_debug_print(vm);
	} else {
		printf("Unrecognized command: '%s'.\n", *cmds);
	}
	{
		int n;
		for (n = 0; n < argc; n++) {
			free(cmds[n]);
		}
		free(cmds);
	}
}

void b_debug_print(b_vm_t* vm) {
	printf("run\n");
	printf("\tStart or Continue the execute\n");
	printf("quit\n");
	printf("\tQuit the Debug\n");
}

#endif
