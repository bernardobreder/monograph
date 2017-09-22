#ifndef B_OPENGL_H_
#define B_OPENGL_H_

#include "b_platform.h"

#ifdef __WINDOWS__
#include <gl/gl.h>
#include <gl/glut.h>
#elif __LINUX__
#include <GL/gl.h>
#include <GL/glut.h>
#elif __MACOS__
#include <glut/glut.h>
#include <opengl/gl.h>
#include <opengl/glu.h>
#endif

#include "b_array.h"
#include "b_frame.h"
#include "b_util.h"

#endif
