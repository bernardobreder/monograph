#ifndef B_BNI_GUI_H_
#define B_BNI_GUI_H_

#include <b_bni.h>
#ifndef MOBILE
#include "glut.h"
#endif

b_method_id_t b_bni_gui_paint_index(b_vm_t* vm);
b_method_id_t b_bni_gui_fps_index(b_vm_t* vm);
b_method_id_t b_bni_gui_keypress_index(b_vm_t* vm);
b_method_id_t b_bni_gui_keyrelease_index(b_vm_t* vm);

b_class_id_t b_bni_gui_glframe_class_index(b_vm_t* vm);

b_bni_state_t b_bni_glframe_timer(b_vm_t* vm, b_object_t* object, int glutstate);

#endif /* B_BNI_O_H_ */
