#include <bni.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "SDL/SDL.h"

// Screen surface
SDL_Surface *gScreen;

static int color;
static b_vm_t* vm;
static b_object_t* object;
static b_object_t* ographic;
static int WIDTH;
static int HEIGHT;
static char keys[512];

#define PITCH (gScreen->pitch / 4)
#define PI 3.1415926535897932384626433832795f

void render() {
	if (SDL_MUSTLOCK(gScreen))
		if (SDL_LockSurface(gScreen) < 0)
			return;
	b_bni_opcode_push(vm, object);
	b_bni_opcode_push(vm, ographic);
	b_bni_opcode_ojump(vm, b_bni_gui_sdl_sdlframe_paint_mindex(vm));
	if (SDL_MUSTLOCK(gScreen))
		SDL_UnlockSurface(gScreen);
	SDL_UpdateRect(gScreen, 0, 0, WIDTH, HEIGHT);
}

b_bni_state_t breder_gui_sdl_SdlFrame$getWidth(b_vm_t* vm, b_object_t* object) {
	b_bni_new_onumber_define(vm, ovalue, WIDTH);
	return b_bni_set_return(vm, 0, ovalue);
}

b_bni_state_t breder_gui_sdl_SdlFrame$getHeight(b_vm_t* vm, b_object_t* object) {
	b_bni_new_onumber_define(vm, ovalue, HEIGHT);
	return b_bni_set_return(vm, 0, ovalue);
}

b_bni_state_t breder_gui_sdl_SdlFrame$SdlFrame(b_vm_t* vm, b_object_t* object) {
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlFrame$start$breder_lang_Number$breder_lang_Number(b_vm_t* _vm, b_object_t* _object) {
	vm = _vm;
	object = _object;
	b_bni_inc_used(vm, object);
	b_bni_get_param_as_number(vm, owidth, width, 0);
	b_bni_get_param_as_number(vm, oheight, height, 1);
	WIDTH = width;
	HEIGHT = height;
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_TIMER) < 0) {
		fprintf(stderr, "Unable to init SDL: %s\n", SDL_GetError());
		return B_BNI_FAIL;
	}
	gScreen = SDL_SetVideoMode(WIDTH, HEIGHT, 32, SDL_SWSURFACE);
	if (gScreen == NULL) {
		fprintf(stderr, "Unable to set up video: %s\n", SDL_GetError());
		return B_BNI_FAIL;
	}
	if (!b_bni_call_0_return_0_param(vm, object, "() init ()")) {
		return B_BNI_FAIL;
	}
	{
		b_bni_opcode_new(vm, b_bni_gui_sdl_sdlgraphic_cindex(vm));
		b_bni_opcode_ojump(vm, b_bni_gui_sdl_sdlgraphic_mindex(vm));
		ographic = b_bni_opcode_pop(vm);
	}
	while (true) {
		render();
		SDL_Event event;
		while (SDL_PollEvent(&event)) {
			switch (event.type) {
			case SDL_KEYDOWN:
				keys[event.key.keysym.scancode] = 1;
				b_bni_call_0_return_0_param(vm, object, "() keyboard ()");
				break;
			case SDL_KEYUP:
				keys[event.key.keysym.scancode] = 0;
				b_bni_call_0_return_0_param(vm, object, "() keyboard ()");
				if (event.key.keysym.sym == SDLK_ESCAPE)
					return B_BNI_SUCCESS;
				break;
			case SDL_QUIT:
				return B_BNI_SUCCESS;
			}
		}
		SDL_Delay(1000 / 100);
	}
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlFrame$isPressed$breder_lang_Number(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_int(vm, okey, key, 0);
	b_bni_new_oboolean_define(vm, ovalue, keys[key]);
	return b_bni_set_return(vm, 0, ovalue);
}

b_bni_state_t breder_gui_sdl_SdlFrame$finalize(b_vm_t* vm, b_object_t* object) {
	SDL_Quit();
	b_bni_dec_used(vm, object);
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlGraphic$setColor$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_int(vm, ored, r, 0);
	b_bni_get_param_as_int(vm, ogreen, g, 1);
	b_bni_get_param_as_int(vm, oblue, b, 2);
	color = SDL_MapRGB(gScreen->format, r, g, b);
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlGraphic$drawCircle$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_int(vm, ox, x, 0);
	b_bni_get_param_as_int(vm, oy, y, 1);
	b_bni_get_param_as_int(vm, oraio, r, 2);
	int i, j;
	for (i = 0; i < 2 * r; i++) {
		if ((y - r + i) >= 0 && (y - r + i) < HEIGHT) {
			int len = (int) sqrt((float) (r * r - (r - i) * (r - i))) * 2;
			int xofs = x - len / 2;
			if (xofs < 0) {
				len += xofs;
				xofs = 0;
			}
			if (xofs + len >= WIDTH) {
				len -= (xofs + len) - WIDTH;
			}
			int ofs = (y - r + i) * PITCH + xofs;
			for (j = 0; j < len; j++)
				((unsigned int*) gScreen->pixels)[ofs + j] = color;
		}
	}
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlGraphic$drawRect$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_int(vm, ox, x, 0);
	b_bni_get_param_as_int(vm, oy, y, 1);
	b_bni_get_param_as_int(vm, ow, width, 2);
	b_bni_get_param_as_int(vm, oh, height, 3);
	int i, j;
	for (i = 0; i < height; i++) {
		if ((y + i) >= 0 && (y + i) < HEIGHT) {
			int len = width;
			int xofs = x;
			if (xofs < 0) {
				len += xofs;
				xofs = 0;
			}
			if (xofs + len >= WIDTH) {
				len -= (xofs + len) - WIDTH;
			}
			int ofs = (i + y) * PITCH + xofs;
			for (j = 0; j < len; j++)
				((unsigned int*) gScreen->pixels)[ofs + j] = color;
		}
	}
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlGraphic$drawImage$breder_gui_sdl_SdlImage$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_notnull(vm, oimage, 0);
	b_bni_get_param_as_int(vm, ox, x, 1);
	b_bni_get_param_as_int(vm, oy, y, 2);
	b_bni_get_param_as_int(vm, otile, tile, 3);
	b_bni_get_param_as_int(vm, otilesize, tilesize, 4);
	SDL_Surface* surface = b_bni_get_data(vm, oimage);
	if (SDL_MUSTLOCK(surface))
		if (SDL_LockSurface(surface) < 0)
			return B_BNI_SUCCESS;
	int i, j;
	for (i = 0; i < tilesize; i++) {
		int screenofs = x + (y + i) * PITCH;
		int tileofs = (i + tile * tilesize) * (surface->pitch / 4);
		for (j = 0; j < tilesize; j++) {
			((unsigned int*) gScreen->pixels)[screenofs] = ((unsigned int*) surface->pixels)[tileofs];
			screenofs++;
			tileofs++;
		}
	}
	if (SDL_MUSTLOCK(surface))
		SDL_UnlockSurface(surface);
	return B_BNI_SUCCESS;
}

b_bni_state_t breder_gui_sdl_SdlImage$SdlImage$breder_lang_String(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string_declared(vm, ofilename, filename, 0);
	SDL_Surface *temp = SDL_LoadBMP(filename);
	if (!temp) {
		return b_bni_throw_generic_exception(vm, "breder.io.FileNotFoundException");
	}
	SDL_Surface* gTiles = SDL_ConvertSurface(temp, gScreen->format, SDL_SWSURFACE);
	SDL_FreeSurface(temp);
	b_bni_set_data(vm, object, gTiles);
	return B_BNI_SUCCESS;
}
