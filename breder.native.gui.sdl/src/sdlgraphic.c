#include <bni.h>
#include <math.h>
#include "sdlframe.h"

int breder_gui_sdl_SdlGraphic$setColor$breder_lang_Number$breder_lang_Number$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* ored = b_bni_get_param ( vm , 0 ) ;
	b_object_t* ogreen = b_bni_get_param ( vm , 1 ) ;
	b_object_t* oblue = b_bni_get_param ( vm , 2 ) ;
	unsigned int color = 0 ;
	int red , green , blue ;
	if ( ored ) {
		red = ( int ) b_bni_onumber_to_primitive ( vm , ored ) & 0xFF ;
	} else {
		red = 0 ;
	}
	if ( ogreen ) {
		green = ( int ) b_bni_onumber_to_primitive ( vm , ogreen ) & 0xFF ;
	} else {
		green = 0 ;
	}
	if ( oblue ) {
		blue = ( int ) b_bni_onumber_to_primitive ( vm , ogreen ) & 0xFF ;
	} else {
		blue = 0 ;
	}
	data->color = SDL_MapRGB ( data->screen->format , red , green , blue ) ;
	return 1 ;
}

int breder_gui_sdl_SdlGraphic$drawCircle$breder_lang_Number$breder_lang_Number$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* ox = b_bni_get_param ( vm , 0 ) ;
	b_object_t* oy = b_bni_get_param ( vm , 1 ) ;
	b_object_t* or = b_bni_get_param ( vm , 2 ) ;
	int x , y , r ;
	{
		if ( ox ) {
			x = ( int ) b_bni_onumber_to_primitive ( vm , ox ) ;
		} else {
			x = 0 ;
		}
		if ( oy ) {
			y = ( int ) b_bni_onumber_to_primitive ( vm , oy ) ;
		} else {
			y = 0 ;
		}
		if ( or ) {
			r = ( int ) b_bni_onumber_to_primitive ( vm , or ) ;
		} else {
			r = 10 ;
		}
	}
	int i , j ;
	SDL_Surface* screen = data->screen ;
	int width = data->width ;
	int height = data->height ;
	int color = data->color ;
	for ( i = 0 ; i < 2 * r ; i ++ ) {
		if ( ( y - r + i ) >= 0 && ( y - r + i ) < height ) {
			int len = ( int ) sqrt ( ( float ) ( r * r - ( r - i ) * ( r - i ) ) ) * 2 ;
			int xofs = x - len / 2 ;
			if ( xofs < 0 ) {
				len += xofs ;
				xofs = 0 ;
			}
			if ( xofs + len >= width ) {
				len -= ( xofs + len ) - width ;
			}
			int ofs = ( y - r + i ) * PITCH(screen) + xofs ;

			for ( j = 0 ; j < len ; j ++ )
				( ( unsigned int* ) screen->pixels ) [ ofs + j ] = color ;
		}
	}
	return 1 ;
}

int breder_gui_sdl_SdlGraphic$drawRect$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number ( b_vm_t* vm , b_object_t* object ) {
	b_sdlframe_t* data = b_bni_get_data ( vm , object ) ;
	b_object_t* ox = b_bni_get_param ( vm , 0 ) ;
	b_object_t* oy = b_bni_get_param ( vm , 1 ) ;
	b_object_t* ow = b_bni_get_param ( vm , 2 ) ;
	b_object_t* oh = b_bni_get_param ( vm , 3 ) ;
	int x , y , w , h ;
	{
		if ( ox ) {
			x = ( int ) b_bni_onumber_to_primitive ( vm , ox ) ;
		} else {
			x = 0 ;
		}
		if ( oy ) {
			y = ( int ) b_bni_onumber_to_primitive ( vm , oy ) ;
		} else {
			y = 0 ;
		}
		if ( ow ) {
			w = ( int ) b_bni_onumber_to_primitive ( vm , ow ) ;
		} else {
			w = 10 ;
		}
		if ( oh ) {
			h = ( int ) b_bni_onumber_to_primitive ( vm , oh ) ;
		} else {
			h = 10 ;
		}
	}
	int i , j ;
	SDL_Surface* screen = data->screen ;
	int width = data->width ;
	int height = data->height ;
	int color = data->color ;
	for ( i = 0 ; i < h ; i ++ ) {
		if ( ( y + i ) >= 0 && ( y + i ) < height ) {
			int len = w ;
			int xofs = x ;
			if ( xofs < 0 ) {
				len += xofs ;
				xofs = 0 ;
			}
			if ( xofs + len >= width ) {
				len -= ( xofs + len ) - width ;
			}
			int ofs = ( i + y ) * PITCH(screen) + xofs ;
			for ( j = 0 ; j < len ; j ++ )
				( ( unsigned int* ) screen->pixels ) [ ofs + j ] = color ;
		}
	}
	return 1 ;
}

