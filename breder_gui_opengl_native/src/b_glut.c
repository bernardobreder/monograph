#include "native.h"

static int inited = 0;
static b_vm_t* vm = 0;
static b_object_t* ographic = 0;
static b_array_t* oframes = 0;

typedef struct b_bni_glut_t b_bni_glut_t;

struct b_bni_glut_t {
	b_frame_id_t id;
	int width;
	int height;
	char* title;
};

b_bni_class_define(b_bni_class_glgraphic_id, glgraphic_id, "breder.gui.opengl.standard.GlGraphic")
b_bni_method_define(b_bni_method_glgraphic_init_id, glgraphic_init_id, b_bni_class_glgraphic_id, "() GlGraphic ()")
b_bni_method_define(b_bni_method_iglframe_paint_id, iglframe_paint_id, b_bni_class_iglframe_id, "() paint (breder.gui.opengl.IGlGraphic)")
b_bni_method_define(b_bni_method_iglframe_update_id, iglframe_update_id, b_bni_class_iglframe_id, "() update ()")
b_bni_method_define(b_bni_method_iglframe_init_id, iglframe_init_id, b_bni_class_iglframe_id, "() init ()")
b_bni_method_define(b_bni_method_iglframe_reshape_id, iglframe_reshape_id, b_bni_class_iglframe_id, "() reshape (breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_keypress_id, iglframe_keypress_id, b_bni_class_iglframe_id, "() keyPress (breder.lang.standard.Natural,breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_keyrelease_id, iglframe_keyrelease_id, b_bni_class_iglframe_id, "() keyRelease (breder.lang.standard.Natural,breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_mousepress_id, iglframe_mousepress_id, b_bni_class_iglframe_id, "() mousePress (breder.lang.standard.Natural,breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_mouserelease_id, iglframe_mouserelease_id, b_bni_class_iglframe_id, "() mouseRelease (breder.lang.standard.Natural,breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_mousemove_id, iglframe_mousemove_id, b_bni_class_iglframe_id, "() mouseMove (breder.lang.standard.Natural,breder.lang.standard.Natural)")
b_bni_method_define(b_bni_method_iglframe_mousedrag_id, iglframe_mousedrag_id, b_bni_class_iglframe_id, "() mouseDrag (breder.lang.standard.Natural,breder.lang.standard.Natural)")

static b_bni_state_t b_init(b_vm_t* vm) {
	oframes = b_array_new();
	{
		b_bni_new_0_param(vm, oresult, b_bni_method_glgraphic_init_id(vm))
		b_bni_inc_used(vm, oresult);
		ographic = oresult;
	}
	return B_BNI_SUCCESS;
}

static void init (b_vm_t* vm) {
	if (b_init (vm) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

static b_bni_state_t b_update (b_vm_t* vm) {
	int n, size = b_array_size_safely(oframes);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oframe = b_array_get_typed(b_object_t, oframes, n);
		if (b_bni_execute_0_return_0_param (vm, oframe, b_bni_method_iglframe_update_id (vm)) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
	}
	return B_BNI_SUCCESS;
}

static void update () {
	if (b_update (vm) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

static b_bni_state_t b_paint (b_vm_t* vm) {
	int n, size = b_array_size_safely(oframes);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oframe = b_array_get_typed(b_object_t, oframes, n);
		b_bni_execute_0_return_1_param (vm, oframe, b_bni_method_iglframe_paint_id (vm), ographic);
	}
	return B_BNI_SUCCESS;
}

static void paint () {
	if (b_paint (vm) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_key_press (b_vm_t* vm, int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, okey, key);
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_3_param (vm, oframe, b_bni_method_iglframe_keypress_id (vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void key_press (int key, int x, int y) {
	if (b_key_press (vm, key, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_key_release (b_vm_t* vm, int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, okey, key);
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_3_param (vm, oframe, b_bni_method_iglframe_keyrelease_id (vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void key_release (int key, int x, int y) {
	if (b_key_release (vm, key, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_mouse_press (b_vm_t* vm, int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, okey, key);
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_3_param (vm, oframe, b_bni_method_iglframe_mousepress_id (vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void mouse_press (int key, int x, int y) {
	if (b_mouse_press (vm, key, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_mouse_release (b_vm_t* vm, int key, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, okey, key);
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_3_param (vm, oframe, b_bni_method_iglframe_mouserelease_id (vm), okey, ox, oy);
	return B_BNI_SUCCESS;
}

void mouse_release (int key, int x, int y) {
	if (b_mouse_release (vm, key, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_mouse_move (b_vm_t* vm, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_2_param (vm, oframe, b_bni_method_iglframe_mousemove_id (vm), ox, oy);
	return B_BNI_SUCCESS;
}

void mouse_move (int x, int y) {
	if (b_mouse_move (vm, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t b_mouse_drag (b_vm_t* vm, int x, int y) {
	b_frame_t* frame = b_frame_get ();
	b_bni_new_onatural_define (vm, ox, x);
	b_bni_new_onatural_define (vm, oy, y);
	b_object_t* oframe = (b_object_t*)frame->data;
	b_bni_execute_0_return_2_param (vm, oframe, b_bni_method_iglframe_mousedrag_id (vm), ox, oy);
	return B_BNI_SUCCESS;
}

void mouse_drag (int x, int y) {
	if (b_mouse_drag (vm, x, y) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

static b_bni_state_t b_reshape (b_vm_t* vm, int w, int h) {
	b_bni_new_onatural_define (vm, ow, w);
	b_bni_new_onatural_define (vm, oh, h);
	int n, size = b_array_size_safely(oframes);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oframe = b_array_get_typed(b_object_t, oframes, n);
		b_bni_execute_0_return_2_param (vm, oframe, b_bni_method_iglframe_reshape_id (vm), ow, oh);
	}
	return B_BNI_SUCCESS;
}

static void reshape (int w, int h) {
	if (b_reshape (vm, w, h) == B_BNI_FAIL) {
		b_vm_throw_print (vm);
		b_bni_release_throw (vm);
	}
}

b_bni_state_t breder_gui_opengl_standard_GlFrame_GlFrame (b_vm_t* vm, b_object_t* object) {
	b_bni_super_object (vm, object);
	if ( ! inited) {
		inited = 1;
		b_frame_init ();
		init (vm);
	}
	b_bni_glut_t* data = b_memory_alloc_vm_typed (vm, b_bni_glut_t, 1);
	{
		b_object_t* oresult;
		if (b_bni_execute_1_return_0_param (vm, object, & oresult, b_bni_method_iglframe_width_id (vm)) == B_BNI_FAIL) {
			b_memory_free (data);
			return B_BNI_FAIL;
		}
		b_bni_onumber_to_natural_define (vm, width, oresult);
		data->width = width;
	}
	{
		b_object_t* oresult;
		if (b_bni_execute_1_return_0_param (vm, object, & oresult, b_bni_method_iglframe_height_id (vm)) == B_BNI_FAIL) {
			b_memory_free (data);
			return B_BNI_FAIL;
		}
		b_bni_onumber_to_natural_define (vm, height, oresult);
		data->height = height;
	}
	{
		b_object_t* oresult;
		if (b_bni_execute_1_return_0_param (vm, object, & oresult, b_bni_method_iglframe_title_id (vm)) == B_BNI_FAIL) {
			b_memory_free (data);
			return B_BNI_FAIL;
		}
		b_bni_ostring_to_text_define (vm, title, oresult);
		data->title = strdup (title);
	}
	b_frame_id_t id = b_frame_new (data->title, data->width, data->height, object);
	data->id = id;
	{
		b_object_t* oresult;
		if (b_bni_execute_1_return_0_param (vm, object, & oresult, b_bni_method_iglframe_fps_id (vm)) == B_BNI_FAIL) {
			b_memory_free (data);
			return B_BNI_FAIL;
		}
		b_bni_onumber_to_natural_define (vm, fps, oresult);
		b_frame_set_fps (id, fps);
	}
	b_frame_paint (id, paint);
	b_frame_update (id, update);
	b_frame_reshape (id, reshape);
	b_frame_key_press (id, key_press);
	b_frame_key_release (id, key_release);
	b_frame_mouse_press (id, mouse_press);
	b_frame_mouse_release (id, mouse_release);
	b_frame_mouse_move (id, mouse_move);
	b_frame_mouse_drag (id, mouse_drag);
	b_bni_set_data_current (vm, object, data);
	b_array_add (oframes, object);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlFrameManager__startLoop (b_vm_t* _vm) {
	vm = _vm;
	int n, size = b_array_size_safely(oframes);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oframe = b_array_get_typed(b_object_t, oframes, n);
		if (b_bni_execute_0_return_0_param (vm, oframe, b_bni_method_iglframe_init_id (vm)) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
	}
	b_frame_loop ();
	b_bni_sret0 (vm, 0);
}
