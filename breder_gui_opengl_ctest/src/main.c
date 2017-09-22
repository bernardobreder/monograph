#include "b_opengl.h"

void b_paint() {

}

void b_reshape(int w, int h) {

}

int main(void) {
	b_frame_init();
	b_frame_id_t id = b_frame_new();
	b_frame_paint(id, b_paint);
	b_frame_reshape(id, b_reshape);
	b_frame_id_t id2 = b_frame_new();
	b_frame_id_t id3 = b_frame_new();
	b_frame_loop();
	return 0;
}
