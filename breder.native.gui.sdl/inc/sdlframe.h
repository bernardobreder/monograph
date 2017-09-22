#ifndef SDLFRAME_H_
#define SDLFRAME_H_

#include <SDL.h>

#define PITCH(s) (s->pitch / 4)

typedef struct b_sdlframe_t b_sdlframe_t ;

struct b_sdlframe_t {
	b_object_t* ographic;
	SDL_Surface *screen ;
	int width ;
	int height ;
	char* title ;
	unsigned int color ;
	int lastTick;
} ;

#endif
