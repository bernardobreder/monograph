#include <stdlib.h>
#include <string.h>
#include "b_bni.h"
#include "b_opengl.h"

static b_array_t* wins = 0;

b_frame_t* b_frame_get_by_id (b_frame_id_t id) {
	int n, size = b_array_size(wins);
	for (n = 0; n < size; n ++ ) {
		b_frame_t* frame = b_array_get_typed(b_frame_t, wins, n);
		if (frame->id == id) {
			return frame;
		}
	}
	return 0;
}

b_frame_t* b_frame_remove_by_id (b_frame_id_t id) {
	int n, size = b_array_size(wins);
	for (n = 0; n < size; n ++ ) {
		b_frame_t* frame = b_array_get_typed(b_frame_t, wins, n);
		if (frame->id == id) {
			b_array_remove (wins, n);
			return frame;
		}
	}
	return 0;
}

b_frame_t* b_frame_get () {
	return b_frame_get_by_id (glutGetWindow ());
}

b_frame_t* b_frame_remove () {
	return b_frame_remove_by_id (glutGetWindow ());
}

void timer (int glutstate) {
	b_frame_t* frame = b_frame_get ();
	if (frame && frame->fps > 0 && ! glutstate) {
		glutTimerFunc (1000 / frame->fps, timer, 0);
		glutPostRedisplay ();
		b_frame_t* frame = b_frame_get ();
		if (frame && frame->updateFunc) {
			b_call_function(void, frame->updateFunc, ()) ();
		}
	} else {
		glutTimerFunc (1000, timer, 0);
	}
}

void display (void) {
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor (0.0f, 0.0f, 0.0f, 1);
	b_frame_t* frame = b_frame_get ();
	if (frame && frame->paintFunc) {
		b_call_function(void, frame->paintFunc, ()) ();
	}
	glutSwapBuffers ();
}

void reshape (int w, int h) {
	b_frame_t* frame = b_frame_get ();
	if (frame && frame->reshapeFunc) {
		b_call_function(void, frame->reshapeFunc, (int w, int h)) (w, h);
	}
}

void keyPress (int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	frame->keyPressed[key] = 1;
	if (frame && frame->keyPressFunc) {
		b_call_function(void, frame->keyPressFunc, (int key,int w, int h)) (key, x, y);
	}
}

void keyAsciiPress (int key, int x, int y) {
	keyPress (key, x, y);
}

void keySpecialPress (int key, int x, int y) {
	keyPress (key + 512, x, y);
}

void keyRelease (int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	frame->keyPressed[key] = 0;
	if (frame && frame->keyReleaseFunc) {
		b_call_function(void, frame->keyReleaseFunc, (int key,int w, int h)) (key, x, y);
	}
}

void keyAsciiRelease (int key, int x, int y) {
	keyRelease (key, x, y);
}

void keySpecialRelease (int key, int x, int y) {
	keyRelease (key + 512, x, y);
}

void mouse (int button, int state, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	if (state) {
		if (frame && frame->mouseReleaseFunc) {
			b_call_function(void, frame->mouseReleaseFunc, (int button, int x, int y)) (button, x, y);
		}
	} else {
		if (frame && frame->mousePressFunc) {
			b_call_function(void, frame->mousePressFunc, (int button, int x, int y)) (button, x, y);
		}
	}
}

void motion (int x, int y) {
	b_frame_t* frame = b_frame_get ();
	if (frame && frame->mouseMoveFunc) {
		b_call_function(void, frame->mouseMoveFunc, (int x, int y)) (x, y);
	}
}

void drag (int x, int y) {
	b_frame_t* frame = b_frame_get ();
	if (frame && frame->mouseDragFunc) {
		b_call_function(void, frame->mouseDragFunc, (int x, int y)) (x, y);
	}
}

void b_close () {
	b_frame_t* frame = b_frame_remove ();
	glutDestroyWindow (frame->id);
	if ( ! b_array_size(wins)) {
		exit (0);
	}
}

void b_frame_init () {
	{
		int argc = 1;
		char** argv = b_memory_alloc_typed(char*, 2);
		argv[0] = "";
		argv[1] = 0;
		glutInit ( & argc, argv);
		free (argv);
	}
	glutInitDisplayMode (GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
	wins = b_array_new();
}

b_frame_id_t b_frame_new (const char* title, int width, int height, void* data) {
	b_frame_t* frame = b_memory_alloc_typed (b_frame_t, 1);
	frame->width = width;
	frame->height = height;
	frame->title = strdup (title);
	frame->data = data;
	frame->keyPressed = b_memory_alloc_typed (unsigned char, 1024);
	b_array_add (wins, frame);
	glutInitWindowSize (frame->width, frame->height);
	frame->id = glutCreateWindow (frame->title);
	glutDisplayFunc (display);
	glutReshapeFunc (reshape);
	glutKeyboardFunc (keyAsciiPress);
	glutKeyboardUpFunc (keyAsciiRelease);
	glutSpecialFunc (keySpecialPress);
	glutSpecialUpFunc (keySpecialRelease);
	glutMouseFunc (mouse);
	glutMotionFunc (drag);
	glutTimerFunc (0, timer, 0);
	glutPassiveMotionFunc (motion);
	// glutWindowStatusFunc(b_close);
	glutIgnoreKeyRepeat (1);
#ifdef WIN32
#else
	glutWMCloseFunc (b_close);
#endif
	return frame->id;
}

void b_frame_set_title (b_frame_id_t id, const char* title) {
	b_frame_t* frame = b_frame_get_by_id (id);
	free (frame->title);
	frame->title = strdup (title);
}

const char* b_frame_get_title (b_frame_id_t id) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return 0;
	}
	return frame->title;
}

void b_frame_set_fps (b_frame_id_t id, int fps) {
	b_frame_t* frame = b_frame_get_by_id (id);
	frame->fps = fps;
}

int b_frame_get_fps (b_frame_id_t id) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return 0;
	}
	return frame->fps;
}

void b_frame_paint (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->paintFunc = func;
}

void b_frame_update (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->updateFunc = func;
}

void b_frame_reshape (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->reshapeFunc = func;
}

void b_frame_key_press (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->keyPressFunc = func;
}

void b_frame_key_release (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->keyReleaseFunc = func;
}

void b_frame_mouse_press (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->mousePressFunc = func;
}

void b_frame_mouse_release (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->mouseReleaseFunc = func;
}

void b_frame_mouse_drag (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->mouseDragFunc = func;
}

void b_frame_mouse_move (b_frame_id_t id, void* func) {
	b_frame_t* frame = b_frame_get_by_id (id);
	if ( ! frame) {
		return;
	}
	frame->mouseMoveFunc = func;
}

void b_frame_loop () {
	glClearColor (0.0, 0.0, 0.0, 1.0);
	glutMainLoop ();
}
