#include <bni.h>

static int sdlGraphicClassIndex = 0 ;
static int renderer = 0 ;
static int keyUp = 0 ;
static int keyDown = 0 ;

void b_native_func ( char* classname , char* methodname , int methodIndex ) {
	if ( ! strcmp ( classname , "breder.gui.sdl.SdlFrame" ) ) {
		if ( ! strcmp ( methodname , "paint" ) ) {
			renderer = methodIndex ;
		} else if ( ! strcmp ( methodname , "keyUp" ) ) {
			keyUp = methodIndex ;
		} else if ( ! strcmp ( methodname , "keyDown" ) ) {
			keyDown = methodIndex ;
		}
	}
}

void b_class_index ( char* classname , int classindex ) {
	if ( ! strcmp ( classname , "breder.gui.sdl.SdlGraphic" ) ) {
		sdlGraphicClassIndex = classindex ;
	}
}

int b_native_SdlFrame_paint () {
	return renderer ;
}

int b_native_SdlFrame_keyUp () {
	return keyUp ;
}

int b_native_SdlFrame_keyDown () {
	return keyDown ;
}

int b_classindex_SdlGraphic () {
	return sdlGraphicClassIndex ;
}
