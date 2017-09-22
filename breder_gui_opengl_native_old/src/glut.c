#include <breder.h>
#ifdef WIN32
#include <gl/gl.h>
#include <gl/glut.h>
#elif MOBILE
#import <OpenGLES/ES1/gl.h>
#import <OpenGLES/ES1/glext.h>
#else
#include <glut/glut.h>
#include <opengl/gl.h>
#include <opengl/glu.h>
#endif

typedef struct b_bni_frame_t b_bni_frame_t;
struct b_bni_frame_t {
	int id;
	char* keys;
	char* specialkeys;
};

static b_vm_t* vm;
static int inited = 0;
static b_array_t* wins = 0;

void timer(int glutstate);

#define breder_gui_Opengl$getKey(METHOD_NAME, VALUE) \
		b_bni_state_t METHOD_NAME(b_vm_t* vm) { \
			b_bni_new_onumber_define(vm, oresult, VALUE); \
			b_bni_sret1(vm, 0, oresult); \
		}

b_object_t* b_frame_get() {
	int win = glutGetWindow();
	int n, size = b_array_size(wins);
	for (n = 0; n < size; n++) {
		b_object_t* oframe = b_array_get_typed(b_object_t, wins, n);
		b_bni_frame_t* data = b_bni_get_data(vm, oframe);
		if (data->id == win) {
			return oframe;
		}
	}
	return 0;
}

b_bni_state_t b_bni_glframe_timer(b_vm_t* vm, b_object_t* object, int quit) {
	b_bni_execute_1_return_0_param(vm, ofps, object, b_bni_method_glframe$fps_id(vm));
	b_number_t fps = b_bni_onumber_to_primitive(vm, ofps);
	if (fps > 0 && !quit) {
		glutPostRedisplay();
		glutTimerFunc(1000 / fps, timer, 0);
	} else {
		glutTimerFunc(1000, timer, 0);
	}
	return B_BNI_SUCCESS;
}

void timer(int glutstate) {
	b_object_t* object = b_frame_get();
	b_bni_state_t state = b_bni_glframe_timer(vm, object, glutstate);
	if (state == B_BNI_FAIL) {
		b_throw_print(vm);
		b_bni_release_throw(vm);
	}
}

b_bni_state_t b_bni_glframe_display(b_vm_t* vm, b_object_t* object) {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	b_bni_execute_0_return_0_param(vm, object, b_bni_method_glframe$paint_id(vm));
	return B_BNI_SUCCESS;
}

void display(void) {
	b_object_t* object = b_frame_get();
	b_bni_state_t state = b_bni_glframe_display(vm, object);
	if (state == B_BNI_FAIL) {
		b_throw_print(vm);
		b_bni_release_throw(vm);
	}
	glutSwapBuffers();
}

b_bni_state_t b_bni_glframe_reshape(b_vm_t* vm, b_object_t* object, b_object_t* ow, b_object_t* oh) {
	b_bni_execute_0_return_2_param(vm, object, b_bni_method_sizeable$setsize_id(vm), ow, oh);
	b_bni_execute_0_return_2_param(vm, object, b_bni_method_glframe$reshape_id(vm), ow, oh);
	return B_BNI_SUCCESS;
}

void reshape(int w, int h) {
	b_object_t* object = b_frame_get();
	b_object_t* ow = b_bni_new_onumber(vm, w);
	b_object_t* oh = b_bni_new_onumber(vm, h);
	b_bni_state_t state = b_bni_glframe_reshape(vm, object, ow, oh);
	if (state == B_BNI_FAIL) {
		b_throw_print(vm);
		b_bni_release_throw(vm);
	}
}

b_bni_state_t b_bni_glframe_keypress(b_vm_t* vm, b_object_t* object, int key, int x, int y) {
	b_bni_new_onumber_define(vm, okey, key);
	b_bni_new_onumber_define(vm, ox, x);
	b_bni_new_onumber_define(vm, oy, y);
	b_bni_execute_0_return_3_param(vm, object, b_bni_method_glframe$keypress_id(vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void keyPress(int key, int x, int y) {
	b_object_t* object = b_frame_get();
	b_bni_state_t state = b_bni_glframe_keypress(vm, object, key, x, y);
	if (state == B_BNI_FAIL) {
		b_throw_print(vm);
		b_bni_release_throw(vm);
	}
}

void keyAsciiPress(int key, int x, int y) {
	b_object_t* self = b_frame_get();
	b_bni_frame_t* data = b_bni_get_data(vm, self);
	data->keys[key] = 1;
	keyPress(key, x, y);
}

void keySpecialPress(int key, int x, int y) {
	b_object_t* self = b_frame_get();
	b_bni_frame_t* data = b_bni_get_data(vm, self);
	data->specialkeys[key] = 1;
	keyPress(key, x, y);
}

b_bni_state_t b_bni_glframe_keyrelease(b_vm_t* vm, b_object_t* object, int key, int x, int y) {
	b_bni_new_onumber_define(vm, okey, key);
	b_bni_new_onumber_define(vm, ox, x);
	b_bni_new_onumber_define(vm, oy, y);
	b_bni_execute_0_return_3_param(vm, object, b_bni_method_glframe$keyrelease_id(vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void keyRelease(int key, int x, int y) {
	b_object_t* object = b_frame_get();
	b_bni_state_t state = b_bni_glframe_keyrelease(vm, object, key, x, y);
	if (state == B_BNI_FAIL) {
		b_throw_print(vm);
		b_bni_release_throw(vm);
	}
}

void keyAsciiRelease(int key, int x, int y) {
	b_object_t* self = b_frame_get();
	b_bni_frame_t* data = b_bni_get_data(vm, self);
	data->keys[key] = 0;
	keyRelease(key, x, y);
}

void keySpecialRelease(int key, int x, int y) {
	b_object_t* self = b_frame_get();
	b_bni_frame_t* data = b_bni_get_data(vm, self);
	data->specialkeys[key] = 0;
	keyRelease(key, x, y);
}

void mouse(int button, int state, int x, int y) {
	/*b_bni_new_onumber_define(vm, obutton, button);
	 b_bni_new_onumber_define(vm, ostate, state);
	 b_bni_new_onumber_define(vm, ox, x);
	 b_bni_new_onumber_define(vm, oy, y);
	 b_bni_call_0_return_4_param(vm, b_gui_oframe(), "() mouse (breder.lang.Number,breder.lang.Number,breder.lang.Number,breder.lang.Number)", obutton, ostate, ox, oy);*/
}

void motion(int x, int y) {
	/*b_bni_new_onumber_define(vm, ox, x);
	 b_bni_new_onumber_define(vm, oy, y);
	 b_bni_call_0_return_2_param(vm, b_gui_oframe(), "() motion (breder.lang.Number,breder.lang.Number)", ox, oy);*/
}

void drag(int x, int y) {
	/*b_bni_new_onumber_define(vm, ox, x);
	 b_bni_new_onumber_define(vm, oy, y);
	 b_bni_call_0_return_2_param(vm, b_gui_oframe(), "() drag (breder.lang.Number,breder.lang.Number)", ox, oy);*/
}

//static void b_bni_glut_close() {
//	int win = glutGetWindow();
//	int n, size = b_array_size(wins);
//	for (n = 0; n < size; n++) {
//		b_object_t* oframe = b_arrayp_get_typed(b_object_t, wins, n);
//		int* cwin = b_bni_get_data(vm, oframe);
//		if (*cwin == win) {
//			b_array_remove(wins, n);
//			b_object_used(oframe) -= 1;
//		}
//	}
//	glutDestroyWindow(win);
//	if (!b_array_size(wins)) {
//		exit(0);
//	}
//}

void init(b_vm_t* _vm) {
	inited = 1;
	vm = _vm;
	wins = b_array_new();
}

int breder_gui_Opengl$isKeyPressed$breder_gui_GlFrame$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_notnull(vm, oframe, 0);
	b_bni_get_param_as_integer(vm, oindex, index, 1);
	b_bni_frame_t* data = b_bni_get_data(vm, oframe);
	int flag = data->keys[index] != 0;
	b_bni_new_oboolean_define(vm, okey, flag);
	b_bni_sret1(vm, 2, okey);
}

int breder_gui_Opengl$isKeySpecialPressed$breder_gui_GlFrame$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_notnull(vm, oframe, 0);
	b_bni_get_param_as_integer(vm, oindex, index, 1);
	b_bni_frame_t* data = b_bni_get_data(vm, oframe);
	int flag = data->specialkeys[index] != 0;
	b_bni_new_oboolean_define(vm, okey, flag);
	b_bni_sret1(vm, 2, okey);
}

int breder_gui_Opengl$viewport$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ox, x, 0);
	b_bni_get_param_as_number(vm, oy, y, 1);
	b_bni_get_param_as_number(vm, ow, w, 2);
	b_bni_get_param_as_number(vm, oh, h, 3);
	glViewport(x, y, w, h);
	b_bni_sret0(vm, 4);
}

int breder_gui_Opengl$ortho2D$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, oleft, l, 0);
	b_bni_get_param_as_number(vm, oright, r, 1);
	b_bni_get_param_as_number(vm, obottom, b, 2);
	b_bni_get_param_as_number(vm, otop, t, 3);
	gluOrtho2D(l, r, b, t);
	b_bni_sret0(vm, 4);
}

int breder_gui_Opengl$perspective$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ofovy, fovy, 0);
	b_bni_get_param_as_number(vm, oaspect, aspect, 1);
	b_bni_get_param_as_number(vm, oznear, znear, 2);
	b_bni_get_param_as_number(vm, ozfar, zfar, 3);
	gluPerspective(fovy, aspect, znear, zfar);
	b_bni_sret0(vm, 4);
}

int breder_gui_Opengl$lookAt$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, oeyeX, eyeX, 0);
	b_bni_get_param_as_number(vm, oeyeY, eyeY, 1);
	b_bni_get_param_as_number(vm, oeyeZ, eyeZ, 2);
	b_bni_get_param_as_number(vm, ocenterX, centerX, 3);
	b_bni_get_param_as_number(vm, ocenterY, centerY, 4);
	b_bni_get_param_as_number(vm, ocenterZ, centerZ, 5);
	b_bni_get_param_as_number(vm, oupX, upX, 6);
	b_bni_get_param_as_number(vm, oupY, upY, 7);
	b_bni_get_param_as_number(vm, oupZ, upZ, 8);
	gluLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
	b_bni_sret0(vm, 9);
}

int breder_gui_Opengl$loadIdentity(b_vm_t* vm) {
	glLoadIdentity();
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$matrixModeModelView(b_vm_t* vm) {
	glMatrixMode(GL_MODELVIEW);
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$matrixModeProjection(b_vm_t* vm) {
	glMatrixMode(GL_PROJECTION);
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$beginTriangle(b_vm_t* vm) {
	glBegin(GL_TRIANGLES);
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$beginQuad(b_vm_t* vm) {
	glBegin(GL_QUADS);
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$end(b_vm_t* vm) {
	glEnd();
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$push(b_vm_t* vm) {
	glPushMatrix();
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$pop(b_vm_t* vm) {
	glPopMatrix();
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$translate$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ox, x, 0);
	b_bni_get_param_as_number(vm, oy, y, 1);
	b_bni_get_param_as_number(vm, oz, z, 2);
	glTranslated(x, y, z);
	b_bni_sret0(vm, 3);
}

int breder_gui_Opengl$scale$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ox, x, 0);
	b_bni_get_param_as_number(vm, oy, y, 1);
	b_bni_get_param_as_number(vm, oz, z, 2);
	glScaled(x, y, z);
	b_bni_sret0(vm, 3);
}

int breder_gui_Opengl$rotate$breder_lang_Number$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, oa, a, 0);
	b_bni_get_param_as_number(vm, ox, x, 1);
	b_bni_get_param_as_number(vm, oy, y, 2);
	b_bni_get_param_as_number(vm, oz, z, 3);
	glRotated(a, x, y, z);
	b_bni_sret0(vm, 4);
}

int breder_gui_Opengl$vertex$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ox, x, 0);
	b_bni_get_param_as_number(vm, oy, y, 1);
	glVertex2d(x, y);
	b_bni_sret0(vm, 2);
}

int breder_gui_Opengl$vertex$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ox, x, 0);
	b_bni_get_param_as_number(vm, oy, y, 1);
	b_bni_get_param_as_number(vm, oz, z, 2);
	glVertex3d(x, y, z);
	b_bni_sret0(vm, 3);
}

int breder_gui_Opengl$color$breder_lang_Number$breder_lang_Number$breder_lang_Number(b_vm_t* vm) {
	b_bni_get_param_as_number(vm, ored, red, 0);
	b_bni_get_param_as_number(vm, ogreen, green, 1);
	b_bni_get_param_as_number(vm, oblue, blue, 2);
	glColor3d(red, green, blue);
	b_bni_sret0(vm, 3);
}

int breder_gui_Opengl$writeStroke$breder_lang_String(b_vm_t* vm) {
	b_bni_get_param_as_string(vm, otext, text, 0);
	int n, size = b_bni_ostring_to_length(vm, otext);
	glPushMatrix();
	for (n = 0; n < size; n++) {
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, *text++);
	}
	glPopMatrix();
	b_bni_sret0(vm, 1);
}

int breder_gui_Opengl$writeBitmap$breder_lang_String(b_vm_t* vm) {
	b_bni_get_param_as_string(vm, otext, text, 0);
	int n, size = b_bni_ostring_to_length(vm, otext);
	glPushMatrix();
	for (n = 0; n < size; n++) {
		glutStrokeCharacter(GLUT_STROKE_ROMAN, *text++);
	}
	glPopMatrix();
	b_bni_sret0(vm, 1);
}

int breder_gui_Opengl$start(b_vm_t* vm) {
	if (!inited) {
		init(vm);
	}
	{
		int argc = 1;
		char** argv = calloc(2, sizeof(char*));
		argv[0] = "";
		argv[1] = 0;
		glutInit(&argc, argv);
		free(argv);
	}
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
	int n, size = b_array_size(wins);
	for (n = 0; n < size; n++) {
		b_object_t* oframe = b_array_get_typed(b_object_t, wins, n);
		b_bni_execute_0_return_0_param(vm, oframe, b_bni_method_glframe$init_id(vm));
		int width;
		{
			b_bni_execute_1_return_0_param(vm, object, oframe, b_bni_method_sizeable$getwidth_id(vm));
			if (object) {
				width = b_bni_onumber_to_primitive(vm, object);
			} else {
				width = 640;
			}
		}
		int height;
		{
			b_bni_execute_1_return_0_param(vm, object, oframe, b_bni_method_sizeable$getheight_id(vm));
			if (object) {
				height = b_bni_onumber_to_primitive(vm, object);
			} else {
				height = 480;
			}
		}
		const char* title;
		{
			b_bni_execute_1_return_0_param(vm, object, oframe, b_bni_method_titleable$gettitle_id(vm));
			if (object) {
				title = b_bni_ostring_to_text(vm, object);
			} else {
				title = "";
			}
		}
		glutInitWindowSize(width, height);
		int win = glutCreateWindow(title);
		b_bni_frame_t* data = b_bni_get_data(vm, oframe);
		data->id = win;
		glClearColor(0.0, 0.0, 0.0, 1.0);
		glutDisplayFunc(display);
		glutReshapeFunc(reshape);
		glutKeyboardFunc(keyAsciiPress);
		glutKeyboardUpFunc(keyAsciiRelease);
		glutSpecialFunc(keySpecialPress);
		glutSpecialUpFunc(keySpecialRelease);
		glutMouseFunc(mouse);
		glutMotionFunc(drag);
		glutTimerFunc(0, timer, 0);
		glutPassiveMotionFunc(motion);
		//glutWindowStatusFunc(b_bni_glut_close);
	}
	glutIgnoreKeyRepeat(1);
	glutMainLoop();
	b_bni_sret0(vm, 0);
}

int breder_gui_Opengl$show$breder_gui_GlFrame(b_vm_t* vm, b_object_t* self) {
	b_object_t* oframe = b_bni_get_param(vm, 0);
	if (!inited) {
		init(vm);
	}
	b_object_used(oframe) += 1;
	b_array_add(wins, oframe);
	b_bni_sret0(vm, 1);
}

breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF1, 1)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF2, 2)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF3, 3)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF4, 4)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF5, 5)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF6, 6)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF7, 7)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF8, 8)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF9, 9)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF10, 10)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF11, 11)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyF12, 12)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyLeft, 100)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyUp, 101)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyRight, 102)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyDown, 103)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyPageUp, 104)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyPageDown, 105)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyHome, 106)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyEnd, 107)
breder_gui_Opengl$getKey(breder_gui_Opengl$getKeyInsert, 108)

int breder_gui_GlFrame$GlFrame(b_vm_t* vm, b_object_t* self) {
	b_bni_frame_t* data = calloc(1, sizeof(b_bni_frame_t));
	data->keys = calloc(256, sizeof(char));
	data->specialkeys = calloc(256, sizeof(char));
	b_bni_set_data(vm, self, data);
	b_bni_sret0(vm, 0);
}

