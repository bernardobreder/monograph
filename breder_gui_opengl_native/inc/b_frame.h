#ifndef B_FRAME_H_
#define B_FRAME_H_

typedef int b_frame_id_t;

typedef struct b_frame_t b_frame_t;

struct b_frame_t {
	b_frame_id_t id;
	unsigned char* keyPressed;
	void* paintFunc;
	void* updateFunc;
	void* reshapeFunc;
	void* keyPressFunc;
	void* keyReleaseFunc;
	void* mousePressFunc;
	void* mouseReleaseFunc;
	void* mouseDragFunc;
	void* mouseMoveFunc;
	int width;
	int height;
	int fps;
	char* title;
	void* data;
};

b_frame_id_t b_frame_new(const char* title, int width, int height, void* data);

b_frame_t* b_frame_get();

void b_frame_set_title(b_frame_id_t, const char*);

const char* b_frame_get_title(b_frame_id_t);

void b_frame_set_fps(b_frame_id_t, int);

int b_frame_get_fps(b_frame_id_t);

void b_frame_paint(b_frame_id_t, void*);

void b_frame_reshape(b_frame_id_t, void*);

void b_frame_key_press(b_frame_id_t, void*);

void b_frame_key_release(b_frame_id_t, void*);

void b_frame_mouse_press(b_frame_id_t, void*);

void b_frame_mouse_release(b_frame_id_t, void*);

void b_frame_mouse_drag(b_frame_id_t, void*);

void b_frame_mouse_move(b_frame_id_t, void*);

void b_frame_loop();

#endif /* B_FRAME_H_ */
