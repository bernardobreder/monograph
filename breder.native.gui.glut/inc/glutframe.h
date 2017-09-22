#ifndef GLUTFRAME_H_
#define GLUTFRAME_H_

#ifdef WIN32
#include <windows.h>
#endif
#include <glut.h>

#define PITCH(s) (s->pitch / 4)

typedef struct b_glutframe_t b_glutframe_t ;

struct b_glutframe_t {
	int width ;
	int height ;
	char* title ;
	unsigned int color ;
} ;

#endif
