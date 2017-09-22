#include <bni.h>

static int run = 0 ;

void b_native_func ( char* classname , char* methodname , int methodIndex ) {
	if ( ! strcmp ( classname , "breder.thread.Thread" ) ) {
		if ( ! strcmp ( methodname , "run" ) ) {
			run = methodIndex ;
		}
	}
}

int b_classindex_Thread_run () {
	return run ;
}
