#ifdef WIN32
#include <gl/gl.h>
#include <gl/glut.h>
#elif MOBILE
#import <OpenGLES/ES1/gl.h>
#import <OpenGLES/ES1/glext.h>
#import <QuartzCore/QuartzCore.h>
#else
#include <glut/glut.h>
#include <opengl/gl.h>
#include <opengl/glu.h>
#endif
