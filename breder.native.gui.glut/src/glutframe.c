#include <bni.h>
#include "glutframe.h"

static b_object_t* oGlutFrame ;
static b_vm_t* cVm ;

int breder_ui_glut_GlutFrame$GlutFrame ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = calloc ( 1 , sizeof(b_glutframe_t) ) ;
	data->width = 480 ;
	data->height = 320 ;
	data->title = "Breder App" ;
	b_bni_set_data ( vm , object , data ) ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$setWidth$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( ! oparam ) b_bni_throw_null_pointer_exception ( vm ) ;
	int param = ( int ) b_bni_onumber_to_primitive ( vm , oparam ) ;
	data->width = param ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$getWidth ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_onumber ( vm , data->width ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$setHeight$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( ! oparam ) b_bni_throw_null_pointer_exception ( vm ) ;
	int param = ( int ) b_bni_onumber_to_primitive ( vm , oparam ) ;
	data->height = param ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$getHeight ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_onumber ( vm , data->height ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$setTitle$breder_lang_String ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( ! oparam ) b_bni_throw_null_pointer_exception ( vm ) ;
	data->title = b_bni_ostring_to_text ( vm , oparam ) ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$getTitle ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_ostring ( vm , data->title ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_ui_glut_GlutFrame$finalize ( b_vm_t* vm , b_object_t* object ) {
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	free ( data ) ;
	return 1 ;
}

static GLfloat spin = 0.0 ;

void init ( void ) {
}

void display ( void ) {
	glutSwapBuffers () ;
}

void spinDisplay ( void ) {
	spin = spin + 2.0 ;
	if ( spin > 360.0 ) spin = spin - 360.0 ;
	glutPostRedisplay () ;
}

void reshape ( int w , int h ) {
}

void mouse ( int button , int state , int x , int y ) {
	switch ( button ) {
		case GLUT_LEFT_BUTTON :
			if ( state == GLUT_DOWN ) glutIdleFunc ( spinDisplay ) ;
			break ;
		case GLUT_MIDDLE_BUTTON :
			if ( state == GLUT_DOWN ) glutIdleFunc ( NULL ) ;
			break ;
		default :
			break ;
	}
}

static void window ( int state ) {
}

int breder_ui_glut_GlutFrame$start ( b_vm_t* vm , b_object_t* object ) {
	oGlutFrame = object ;
	cVm = vm ;
	b_glutframe_t* data = b_bni_get_data ( vm , object ) ;
	b_bni_inc_used ( vm , object ) ;
	glutInitDisplayMode ( GLUT_DOUBLE | GLUT_RGB ) ;
	glutInitWindowSize ( data->width , data->height ) ;
	glutInitWindowPosition ( 100 , 100 ) ;
	glutCreateWindow ( data->title ) ;
	init () ;
	glutDisplayFunc ( display ) ;
	glutReshapeFunc ( reshape ) ;
	glutMouseFunc ( mouse ) ;
	glutWindowStatusFunc ( window ) ;
	glutMainLoop () ;
	b_bni_dec_used ( vm , object ) ;
	return 0 ;
}
