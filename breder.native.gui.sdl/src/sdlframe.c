#include <bni.h>
#include "sdlframe.h"

static b_object_t* oSdlFrame ;
static b_vm_t* cVm ;

#define BALLCOLOR 0x007fff
#define BGCOLOR 0x5f0000
#define WALLCOLOR 0x9f1f1f
#define FPS 100
#define PI 3.1415926535897932384626433832795f

void render () {
	b_sdlframe_t* data = b_bni_get_data ( cVm , oSdlFrame ) ;
	int tick = SDL_GetTicks () ;
	if ( tick <= data->lastTick + 1000 / FPS ) {
		SDL_Delay ( 1000 / FPS ) ;
		return ;
	}
	data->lastTick = tick ;
	if ( SDL_MUSTLOCK ( data->screen ) ) if ( SDL_LockSurface ( data->screen ) < 0 ) return ;
	{
		b_bni_push ( cVm , oSdlFrame ) ;
		b_bni_push ( cVm , data->ographic ) ;
		b_bni_call ( cVm , oSdlFrame , b_native_SdlFrame_paint () ) ;
		b_bni_dec ( cVm , 2 ) ;
	}
	if ( SDL_MUSTLOCK ( data->screen ) ) SDL_UnlockSurface ( data->screen ) ;
	SDL_UpdateRect ( data->screen , 0 , 0 , data->width , data->height ) ;
}

int breder_gui_sdl_SdlFrame$SdlFrame ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = calloc ( 1 , sizeof(b_sdlframe_t) ) ;
	data->width = 480 ;
	data->height = 320 ;
	data->title = "Breder App" ;
	b_bni_set_data ( vm , object , data ) ;
	return 1 ;
}

int breder_gui_sdl_SdlFrame$setWidth$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( oparam ) {
		int param = ( int ) b_bni_onumber_to_primitive ( vm , oparam ) ;
		data->width = param ;
	}
	return 1 ;
}

int breder_gui_sdl_SdlFrame$getWidth ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_onumber ( vm , data->width ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_gui_sdl_SdlFrame$setHeight$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( oparam ) {
		int param = ( int ) b_bni_onumber_to_primitive ( vm , oparam ) ;
		data->height = param ;
	}
	return 1 ;
}

int breder_gui_sdl_SdlFrame$getHeight ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_onumber ( vm , data->height ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_gui_sdl_SdlFrame$setTitle$breder_lang_String ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oparam = b_bni_get_param ( vm , 0 ) ;
	if ( oparam ) {
		data->title = b_bni_ostring_to_text ( vm , oparam ) ;
	}
	return 1 ;
}

int breder_gui_sdl_SdlFrame$getTitle ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* oresult = b_bni_new_ostring ( vm , data->title ) ;
	b_bni_set_static_return ( vm , 0 , oresult ) ;
	return 1 ;
}

int breder_gui_sdl_SdlFrame$finalize ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	SDL_FreeSurface ( data->screen ) ;
	SDL_Quit () ;
	free ( data ) ;
	return 1 ;
}

int breder_gui_sdl_SdlFrame$start ( b_vm_t* vm , b_object_t* object ) {
	oSdlFrame = object ;
	cVm = vm ;
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_bni_inc_used ( vm , object ) ;
	data->ographic = b_bni_new ( vm , b_classindex_SdlGraphic () ) ;
	b_bni_set_data ( vm , data->ographic , data ) ;
	b_bni_inc_used ( vm , data->ographic ) ;
	if ( SDL_Init ( SDL_INIT_VIDEO ) < 0 ) {
		fprintf ( stderr , "Unable to init SDL: %s\n" , SDL_GetError () ) ;
		exit ( 1 ) ;
	}
	data->screen = SDL_SetVideoMode ( data->width , data->height , 32 , SDL_SWSURFACE ) ;
	if ( data->screen == NULL ) {
		fprintf ( stderr , "Unable to set up video: %s\n" , SDL_GetError () ) ;
		exit ( 1 ) ;
	}
	printf("Creating a frame with w=%d h=%d\n",data->width,data->height);
	SDL_WM_SetCaption ( data->title , 0 ) ;
	int stop = 0 ;
	while ( ! stop ) {
		render () ;
		SDL_Event event ;
		while ( SDL_PollEvent ( & event ) ) {
			switch ( event.type ) {
				case SDL_KEYDOWN : {
					{
						b_bni_push ( cVm , oSdlFrame ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , 0 ) ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , 0 ) ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , ( int ) event.key.keysym.sym ) ) ;
						b_bni_call ( cVm , oSdlFrame , b_native_SdlFrame_keyDown () ) ;
						b_bni_dec ( vm , 4 ) ;
					}
					break ;
				}
				case SDL_KEYUP : {
					{
						b_bni_push ( cVm , oSdlFrame ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , 0 ) ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , 0 ) ) ;
						b_bni_push ( cVm , b_bni_new_onumber ( vm , ( int ) event.key.keysym.sym ) ) ;
						b_bni_call ( cVm , oSdlFrame , b_native_SdlFrame_keyUp () ) ;
						b_bni_dec ( vm , 4 ) ;
					}
					break ;
				}
				case SDL_QUIT :
					stop = 1 ;
					break ;
			}
			if ( stop ) {
				break ;
			}
		}
	}
	b_bni_dec_used ( vm , data->ographic ) ;
	b_bni_dec_used ( vm , object ) ;
	return 0 ;
}
