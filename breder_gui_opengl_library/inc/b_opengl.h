#ifndef B_OPENGL_H_
#define B_OPENGL_H_

#ifdef WIN32
#include <gl/gl.h>
#include <gl/glut.h>
#else
#include <glut/glut.h>
#include <opengl/gl.h>
#include <opengl/glu.h>
#endif

#include "b_array.h"
#include "b_frame.h"
#include "b_util.h"

#endif
