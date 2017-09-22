#include <bni.h>

static b_class_id_t sdlframe_cindex = 0;
static b_class_id_t sdlgraphic_cindex = 0;
static b_method_id_t paint_mindex = 0;
static b_method_id_t sdlgraphic_mindex = 0;

b_class_id_t b_bni_gui_sdl_sdlframe_cindex(b_vm_t* vm) {
	if (!sdlframe_cindex)
		sdlframe_cindex = b_vm_get_class(vm, "breder.gui.sdl.SdlFrame")->index;
	return sdlframe_cindex;
}

b_class_id_t b_bni_gui_sdl_sdlgraphic_cindex(b_vm_t* vm) {
	if (!sdlgraphic_cindex)
		sdlgraphic_cindex = b_vm_get_class(vm, "breder.gui.sdl.SdlGraphic")->index;
	return sdlgraphic_cindex;
}

b_method_id_t b_bni_gui_sdl_sdlframe_paint_mindex(b_vm_t* vm) {
	if (!paint_mindex)
		paint_mindex = b_vm_get_method(vm, b_bni_gui_sdl_sdlframe_cindex(vm), "() paint (breder.gui.sdl.SdlGraphic)")->index;
	return paint_mindex;
}

b_method_id_t b_bni_gui_sdl_sdlgraphic_mindex(b_vm_t* vm) {
	if (!sdlgraphic_mindex)
		sdlgraphic_mindex = b_vm_get_method(vm, b_bni_gui_sdl_sdlgraphic_cindex(vm), "() SdlGraphic ()")->index;
	return sdlgraphic_mindex;
}

