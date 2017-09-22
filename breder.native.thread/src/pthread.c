#include <bni.h>
#include <pthread.h>

int breder_thread_Thread$Thread ( b_vm_t* vm , b_object_t* object ) {
	pthread_t* pthread = malloc ( sizeof(pthread_t) ) ;
	b_bni_set_data ( vm , object , pthread ) ;
	return 1 ;
}

void* pthread_run ( void* data ) {
	b_vm_t* vm = ( b_vm_t* ) ( ( void** ) data ) [ 0 ] ;
	b_object_t* object = ( b_object_t* ) ( ( void** ) data ) [ 1 ] ;
	b_bni_call ( vm , object , b_classindex_Thread_run() ) ;
	free ( data ) ;
	b_bni_dec_used ( vm , object ) ;
	pthread_exit ( NULL ) ;
	return 0 ;
}

int breder_thread_Thread$start ( b_vm_t* vm , b_object_t* object ) {
	pthread_t* pthread = b_bni_get_data ( vm , object ) ;
	void** data = malloc ( sizeof(void*) * 2 ) ;
	data [ 0 ] = vm ;
	data [ 1 ] = object ;
	b_bni_inc_used ( vm , object ) ;
	int state = pthread_create ( pthread , NULL , pthread_run , data ) ;
	if ( state ) return b_bni_throw_generic_exception ( vm , "breder.thread.ThreadRuntimeException" ) ; // printf ( "ERROR; return code from pthread_create() is %d\n" , rc ) ;
	return 1 ;
}

int breder_thread_Thread$finalize ( b_vm_t* vm , b_object_t* object ) {
	pthread_t* pthread = b_bni_get_data ( vm , object ) ;
	free ( pthread ) ;
}
