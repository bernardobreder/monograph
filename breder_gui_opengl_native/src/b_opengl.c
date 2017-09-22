#include "native.h"

b_bni_state_t breder_gui_opengl_standard_GlNative__viewport_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, ow, w, 2);
	b_bni_get_param_as_number (vm, oh, h, 3);
	glViewport (x, y, w, h);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__matrixModeProjection (b_vm_t* vm) {
	glMatrixMode (GL_PROJECTION);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__matrixModeModelView (b_vm_t* vm) {
	glMatrixMode (GL_MODELVIEW);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__loadIdentity (b_vm_t* vm) {
	glLoadIdentity ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__ortho_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oleft, left, 0);
	b_bni_get_param_as_number (vm, oright, right, 1);
	b_bni_get_param_as_number (vm, obottom, bottom, 2);
	b_bni_get_param_as_number (vm, otop, top, 3);
	b_bni_get_param_as_number (vm, ozNear, zNear, 4);
	b_bni_get_param_as_number (vm, ozFar, zFar, 5);
	glOrtho (left, right, bottom, top, zNear, zFar);
	b_bni_sret0 (vm, 6);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__perspective_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ofovy, fovy, 0);
	b_bni_get_param_as_number (vm, oaspect, aspect, 1);
	b_bni_get_param_as_number (vm, oznear, near, 2);
	b_bni_get_param_as_number (vm, ozfar, far, 3);
	gluPerspective (fovy, aspect, near, far);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lookAt_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oeyex, eyex, 0);
	b_bni_get_param_as_number (vm, oeyey, eyey, 1);
	b_bni_get_param_as_number (vm, oeyez, eyez, 2);
	b_bni_get_param_as_number (vm, ocenterx, centerx, 3);
	b_bni_get_param_as_number (vm, ocentery, centery, 4);
	b_bni_get_param_as_number (vm, ocenterz, centerz, 5);
	b_bni_get_param_as_number (vm, oupx, upx, 6);
	b_bni_get_param_as_number (vm, oupy, upy, 7);
	b_bni_get_param_as_number (vm, oupz, upz, 8);
	gluLookAt (eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz);
	b_bni_sret0 (vm, 9);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__color_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ored, red, 0);
	b_bni_get_param_as_number (vm, ogreen, green, 1);
	b_bni_get_param_as_number (vm, oblue, blue, 2);
	glColor3f (red / 255, green / 255, blue / 255);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rect_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, owidth, width, 2);
	b_bni_get_param_as_number (vm, oheight, height, 3);
	glRectf (x, y, width, height);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__vertex_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	glVertex2f (x, y);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__vertex_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, oz, z, 2);
	glVertex3f (x, y, z);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__normal_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, oz, z, 2);
	glNormal3f (x, y, z);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__translate_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	glTranslatef (x, y, 0);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__translate_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, oz, z, 2);
	glTranslatef (x, y, z);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__scale_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	glScalef (x, y, 0);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__scale_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	b_bni_get_param_as_number (vm, oz, z, 2);
	glScalef (x, y, z);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__edge_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, oflag, flag, 0);
	glEdgeFlag (flag);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__edge (b_vm_t* vm) {
	glEdgeFlag (GL_TRUE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateX_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	glRotatef (angle, 1, 0, 0);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateY_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	glRotatef (angle, 0, 1, 0);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateZ_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	glRotatef (angle, 0, 0, 1);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateX_breder_lang_standard_Number_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	b_bni_get_param_as_number (vm, ovalue, value, 1);
	glRotatef (angle, value / 255, 0, 0);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateY_breder_lang_standard_Number_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	b_bni_get_param_as_number (vm, ovalue, value, 1);
	glRotatef (angle, value / 255, 0, 0);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotateZ_breder_lang_standard_Number_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	b_bni_get_param_as_number (vm, ovalue, value, 1);
	glRotatef (angle, value / 255, 0, 0);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotate_breder_lang_standard_Number_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	b_bni_get_param_as_number (vm, ox, x, 1);
	b_bni_get_param_as_number (vm, oy, y, 2);
	glRotatef (angle, x / 255, y / 255, 0);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rotate_breder_lang_standard_Number_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oangle, angle, 0);
	b_bni_get_param_as_number (vm, ox, x, 1);
	b_bni_get_param_as_number (vm, oy, y, 2);
	b_bni_get_param_as_number (vm, oz, z, 3);
	glRotatef (angle, x / 255, y / 255, z / 255);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginPoint (b_vm_t* vm) {
	glBegin (GL_POINTS);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginLineStrip (b_vm_t* vm) {
	glBegin (GL_LINE_STRIP);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginLineLoop (b_vm_t* vm) {
	glBegin (GL_LINE_LOOP);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginTriangle (b_vm_t* vm) {
	glBegin (GL_TRIANGLES);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginTriangleStrip (b_vm_t* vm) {
	glBegin (GL_TRIANGLE_STRIP);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginTriangleFan (b_vm_t* vm) {
	glBegin (GL_TRIANGLE_FAN);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginQuad (b_vm_t* vm) {
	glBegin (GL_QUADS);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginQuadStrip (b_vm_t* vm) {
	glBegin (GL_QUAD_STRIP);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__beginPolygon (b_vm_t* vm) {
	glBegin (GL_POLYGON);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__end (b_vm_t* vm) {
	glEnd ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__push (b_vm_t* vm) {
	glPushMatrix ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__pop (b_vm_t* vm) {
	glPopMatrix ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getPointSizeSmallest (b_vm_t* vm) {
	GLfloat sizes[2];
	glGetFloatv (GL_POINT_SIZE_RANGE, sizes);
	b_bni_new_onumber_define (vm, oresult, sizes[0]);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getPointSizeLargest (b_vm_t* vm) {
	GLfloat sizes[2];
	glGetFloatv (GL_POINT_SIZE_RANGE, sizes);
	b_bni_new_onumber_define (vm, oresult, sizes[1]);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getPointSizeGranularity (b_vm_t* vm) {
	GLfloat step;
	glGetFloatv (GL_POINT_SIZE_GRANULARITY, & step);
	b_bni_new_onumber_define (vm, oresult, step);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__setPointSize_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ovalue, value, 0);
	glPointSize (value);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getLineSizeSmallest (b_vm_t* vm) {
	GLfloat sizes[2];
	glGetFloatv (GL_LINE_WIDTH_RANGE, sizes);
	b_bni_new_onumber_define (vm, oresult, sizes[0]);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getLineSizeLargest (b_vm_t* vm) {
	GLfloat sizes[2];
	glGetFloatv (GL_LINE_WIDTH_RANGE, sizes);
	b_bni_new_onumber_define (vm, oresult, sizes[1]);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getLineSizeGranularity (b_vm_t* vm) {
	GLfloat step;
	glGetFloatv (GL_LINE_WIDTH_GRANULARITY, & step);
	b_bni_new_onumber_define (vm, oresult, step);
	b_bni_sret1 (vm, 0, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__setLineSize_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ovalue, value, 0);
	glLineWidth (value);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableCullFace (b_vm_t* vm) {
	glEnable (GL_CULL_FACE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableCullFace (b_vm_t* vm) {
	glDisable (GL_CULL_FACE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableDepthMask (b_vm_t* vm) {
	glDepthMask (GL_TRUE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableDepthMask (b_vm_t* vm) {
	glDepthMask (GL_FALSE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableDepthTest (b_vm_t* vm) {
	glEnable (GL_DEPTH_TEST);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableDepthTest (b_vm_t* vm) {
	glDisable (GL_DEPTH_TEST);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__shadeModelFlat (b_vm_t* vm) {
	glShadeModel (GL_FLAT);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__shadeModelSmooth (b_vm_t* vm) {
	glShadeModel (GL_SMOOTH);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__shade_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, oflag, flag, 0);
	if (flag) {
		glShadeModel (GL_SMOOTH);
	} else {
		glShadeModel (GL_FLAT);
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__polygonModeBackLine (b_vm_t* vm) {
	glPolygonMode (GL_BACK, GL_LINE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__polygonModeBackFill (b_vm_t* vm) {
	glPolygonMode (GL_BACK, GL_FILL);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__polygonModeFrontLine (b_vm_t* vm) {
	glPolygonMode (GL_FRONT, GL_LINE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__polygonModeFrontFill (b_vm_t* vm) {
	glPolygonMode (GL_FRONT, GL_FILL);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__faceFront (b_vm_t* vm) {
	glFrontFace (GL_CW);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__faceBack (b_vm_t* vm) {
	glFrontFace (GL_CCW);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawBackBuffer (b_vm_t* vm) {
	glDrawBuffer (GL_BACK);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawFrontBuffer (b_vm_t* vm) {
	glDrawBuffer (GL_FRONT);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lineStipple_breder_lang_standard_Natural_breder_lang_standard_String (b_vm_t* vm) {
	b_bni_get_param_as_natural (vm, ofactor, factor, 0);
	b_bni_get_param_as_string (vm, opattern, strpattern, 1);
	GLushort pattern = 0;
	if (strlen (strpattern) == 4) {
		int n;
		for (n = 0; n < 4; n ++ ) {
			int c = strpattern[n];
			if ( (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F')) {
				if (c >= '0' && c <= '9') {
					c -= '0';
				} else {
					c -= 'A' + 10;
				}
				pattern += c << (sizeof(GLushort) * 8 - n * 4);
			}
		}
	}
	glLineStipple (factor, pattern);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableLineStipple (b_vm_t* vm) {
	glEnable (GL_LINE_STIPPLE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableLineStipple (b_vm_t* vm) {
	glDisable (GL_LINE_STIPPLE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableNormalize (b_vm_t* vm) {
	glEnable (GL_NORMALIZE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableNormalize (b_vm_t* vm) {
	glDisable (GL_NORMALIZE);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableBlend (b_vm_t* vm) {
	glEnable (GL_BLEND);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableBlend (b_vm_t* vm) {
	glDisable (GL_BLEND);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableAlphaTest (b_vm_t* vm) {
	glEnable (GL_ALPHA_TEST);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableAlphaTest (b_vm_t* vm) {
	glDisable (GL_ALPHA_TEST);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableLighting (b_vm_t* vm) {
	glEnable (GL_LIGHTING);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableLighting (b_vm_t* vm) {
	glDisable (GL_LIGHTING);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableMaterialColor (b_vm_t* vm) {
	glEnable (GL_COLOR_MATERIAL);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableMaterialColor (b_vm_t* vm) {
	glDisable (GL_COLOR_MATERIAL);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableLight_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	switch (index) {
		case 1 :
			glEnable (GL_LIGHT0);
			break;
		case 2 :
			glEnable (GL_LIGHT1);
			break;
		case 3 :
			glEnable (GL_LIGHT2);
			break;
		case 4 :
			glEnable (GL_LIGHT3);
			break;
		case 5 :
			glEnable (GL_LIGHT4);
			break;
		case 6 :
			glEnable (GL_LIGHT5);
			break;
		case 7 :
			glEnable (GL_LIGHT6);
			break;
		case 8 :
			glEnable (GL_LIGHT7);
			break;
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableLight_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	switch (index) {
		case 1 :
			glDisable (GL_LIGHT0);
			break;
		case 2 :
			glDisable (GL_LIGHT1);
			break;
		case 3 :
			glDisable (GL_LIGHT2);
			break;
		case 4 :
			glDisable (GL_LIGHT3);
			break;
		case 5 :
			glDisable (GL_LIGHT4);
			break;
		case 6 :
			glDisable (GL_LIGHT5);
			break;
		case 7 :
			glDisable (GL_LIGHT6);
			break;
		case 8 :
			glDisable (GL_LIGHT7);
			break;
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightAmbient_breder_lang_standard_Index_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_natural (vm, ox, x, 1);
	b_bni_get_param_as_natural (vm, oy, y, 2);
	b_bni_get_param_as_natural (vm, oz, z, 3);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	switch (index) {
		case 1 :
			glLightfv (GL_LIGHT0, GL_AMBIENT, v);
			break;
		case 2 :
			glLightfv (GL_LIGHT1, GL_AMBIENT, v);
			break;
		case 3 :
			glLightfv (GL_LIGHT2, GL_AMBIENT, v);
			break;
		case 4 :
			glLightfv (GL_LIGHT3, GL_AMBIENT, v);
			break;
		case 5 :
			glLightfv (GL_LIGHT4, GL_AMBIENT, v);
			break;
		case 6 :
			glLightfv (GL_LIGHT5, GL_AMBIENT, v);
			break;
		case 7 :
			glLightfv (GL_LIGHT6, GL_AMBIENT, v);
			break;
		case 8 :
			glLightfv (GL_LIGHT7, GL_AMBIENT, v);
			break;
	}
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightSpecular_breder_lang_standard_Index_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_natural (vm, ox, x, 1);
	b_bni_get_param_as_natural (vm, oy, y, 2);
	b_bni_get_param_as_natural (vm, oz, z, 3);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	switch (index) {
		case 1 :
			glLightfv (GL_LIGHT0, GL_SPECULAR, v);
			break;
		case 2 :
			glLightfv (GL_LIGHT1, GL_SPECULAR, v);
			break;
		case 3 :
			glLightfv (GL_LIGHT2, GL_SPECULAR, v);
			break;
		case 4 :
			glLightfv (GL_LIGHT3, GL_SPECULAR, v);
			break;
		case 5 :
			glLightfv (GL_LIGHT4, GL_SPECULAR, v);
			break;
		case 6 :
			glLightfv (GL_LIGHT5, GL_SPECULAR, v);
			break;
		case 7 :
			glLightfv (GL_LIGHT6, GL_SPECULAR, v);
			break;
		case 8 :
			glLightfv (GL_LIGHT7, GL_SPECULAR, v);
			break;
	}
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightDiffuse_breder_lang_standard_Index_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_natural (vm, ox, x, 1);
	b_bni_get_param_as_natural (vm, oy, y, 2);
	b_bni_get_param_as_natural (vm, oz, z, 3);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	switch (index) {
		case 1 :
			glLightfv (GL_LIGHT0, GL_DIFFUSE, v);
			break;
		case 2 :
			glLightfv (GL_LIGHT1, GL_DIFFUSE, v);
			break;
		case 3 :
			glLightfv (GL_LIGHT2, GL_DIFFUSE, v);
			break;
		case 4 :
			glLightfv (GL_LIGHT3, GL_DIFFUSE, v);
			break;
		case 5 :
			glLightfv (GL_LIGHT4, GL_DIFFUSE, v);
			break;
		case 6 :
			glLightfv (GL_LIGHT5, GL_DIFFUSE, v);
			break;
		case 7 :
			glLightfv (GL_LIGHT6, GL_DIFFUSE, v);
			break;
		case 8 :
			glLightfv (GL_LIGHT7, GL_DIFFUSE, v);
			break;
	}
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightPosition_breder_lang_standard_Index_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_natural (vm, ox, x, 1);
	b_bni_get_param_as_natural (vm, oy, y, 2);
	b_bni_get_param_as_natural (vm, oz, z, 3);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	switch (index) {
		case 1 :
			glLightfv (GL_LIGHT0, GL_POSITION, v);
			break;
		case 2 :
			glLightfv (GL_LIGHT1, GL_POSITION, v);
			break;
		case 3 :
			glLightfv (GL_LIGHT2, GL_POSITION, v);
			break;
		case 4 :
			glLightfv (GL_LIGHT3, GL_POSITION, v);
			break;
		case 5 :
			glLightfv (GL_LIGHT4, GL_POSITION, v);
			break;
		case 6 :
			glLightfv (GL_LIGHT5, GL_POSITION, v);
			break;
		case 7 :
			glLightfv (GL_LIGHT6, GL_POSITION, v);
			break;
		case 8 :
			glLightfv (GL_LIGHT7, GL_POSITION, v);
			break;
	}
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightSpotDirection_breder_lang_standard_Index_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_number (vm, ox, x, 1);
	b_bni_get_param_as_number (vm, oy, y, 2);
	b_bni_get_param_as_number (vm, oz, z, 3);
	float v[] = {x, y, z };
	switch (index) {
		case 1 :
			glLightfv (GL_LIGHT0, GL_SPOT_DIRECTION, v);
			break;
		case 2 :
			glLightfv (GL_LIGHT1, GL_SPOT_DIRECTION, v);
			break;
		case 3 :
			glLightfv (GL_LIGHT2, GL_SPOT_DIRECTION, v);
			break;
		case 4 :
			glLightfv (GL_LIGHT3, GL_SPOT_DIRECTION, v);
			break;
		case 5 :
			glLightfv (GL_LIGHT4, GL_SPOT_DIRECTION, v);
			break;
		case 6 :
			glLightfv (GL_LIGHT5, GL_SPOT_DIRECTION, v);
			break;
		case 7 :
			glLightfv (GL_LIGHT6, GL_SPOT_DIRECTION, v);
			break;
		case 8 :
			glLightfv (GL_LIGHT7, GL_SPOT_DIRECTION, v);
			break;
	}
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightSpotExponent_breder_lang_standard_Index_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_number (vm, oangle, angle, 1);
	switch (index) {
		case 1 :
			glLightf (GL_LIGHT0, GL_SPOT_EXPONENT, angle);
			break;
		case 2 :
			glLightf (GL_LIGHT1, GL_SPOT_EXPONENT, angle);
			break;
		case 3 :
			glLightf (GL_LIGHT2, GL_SPOT_EXPONENT, angle);
			break;
		case 4 :
			glLightf (GL_LIGHT3, GL_SPOT_EXPONENT, angle);
			break;
		case 5 :
			glLightf (GL_LIGHT4, GL_SPOT_EXPONENT, angle);
			break;
		case 6 :
			glLightf (GL_LIGHT5, GL_SPOT_EXPONENT, angle);
			break;
		case 7 :
			glLightf (GL_LIGHT6, GL_SPOT_EXPONENT, angle);
			break;
		case 8 :
			glLightf (GL_LIGHT7, GL_SPOT_EXPONENT, angle);
			break;
	}
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightSpotCutOff_breder_lang_standard_Index_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_number (vm, oangle, angle, 1);
	switch (index) {
		case 1 :
			glLightf (GL_LIGHT0, GL_SPOT_CUTOFF, angle);
			break;
		case 2 :
			glLightf (GL_LIGHT1, GL_SPOT_CUTOFF, angle);
			break;
		case 3 :
			glLightf (GL_LIGHT2, GL_SPOT_CUTOFF, angle);
			break;
		case 4 :
			glLightf (GL_LIGHT3, GL_SPOT_CUTOFF, angle);
			break;
		case 5 :
			glLightf (GL_LIGHT4, GL_SPOT_CUTOFF, angle);
			break;
		case 6 :
			glLightf (GL_LIGHT5, GL_SPOT_CUTOFF, angle);
			break;
		case 7 :
			glLightf (GL_LIGHT6, GL_SPOT_CUTOFF, angle);
			break;
		case 8 :
			glLightf (GL_LIGHT7, GL_SPOT_CUTOFF, angle);
			break;
	}
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightConstantAttenuation_breder_lang_standard_Index_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_number (vm, oangle, angle, 1);
	switch (index) {
		case 1 :
			glLightf (GL_LIGHT0, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 2 :
			glLightf (GL_LIGHT1, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 3 :
			glLightf (GL_LIGHT2, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 4 :
			glLightf (GL_LIGHT3, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 5 :
			glLightf (GL_LIGHT4, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 6 :
			glLightf (GL_LIGHT5, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 7 :
			glLightf (GL_LIGHT6, GL_CONSTANT_ATTENUATION, angle);
			break;
		case 8 :
			glLightf (GL_LIGHT7, GL_CONSTANT_ATTENUATION, angle);
			break;
	}
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightLinearAttenuation_breder_lang_standard_Index_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_as_number (vm, oangle, angle, 1);
	switch (index) {
		case 1 :
			glLightf (GL_LIGHT0, GL_LINEAR_ATTENUATION, angle);
			break;
		case 2 :
			glLightf (GL_LIGHT1, GL_LINEAR_ATTENUATION, angle);
			break;
		case 3 :
			glLightf (GL_LIGHT2, GL_LINEAR_ATTENUATION, angle);
			break;
		case 4 :
			glLightf (GL_LIGHT3, GL_LINEAR_ATTENUATION, angle);
			break;
		case 5 :
			glLightf (GL_LIGHT4, GL_LINEAR_ATTENUATION, angle);
			break;
		case 6 :
			glLightf (GL_LIGHT5, GL_LINEAR_ATTENUATION, angle);
			break;
		case 7 :
			glLightf (GL_LIGHT6, GL_LINEAR_ATTENUATION, angle);
			break;
		case 8 :
			glLightf (GL_LIGHT7, GL_LINEAR_ATTENUATION, angle);
			break;
	}
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__lightModelAmbient_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_natural (vm, ox, x, 0);
	b_bni_get_param_as_natural (vm, oy, y, 1);
	b_bni_get_param_as_natural (vm, oz, z, 2);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	glLightModelfv (GL_LIGHT_MODEL_AMBIENT, v);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialAmbientDiffuse_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ox, x, 2);
	b_bni_get_param_as_natural (vm, oy, y, 3);
	b_bni_get_param_as_natural (vm, oz, z, 4);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_AMBIENT_AND_DIFFUSE, v);
	}
	b_bni_sret0 (vm, 5);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialAmbient_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ox, x, 2);
	b_bni_get_param_as_natural (vm, oy, y, 3);
	b_bni_get_param_as_natural (vm, oz, z, 4);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_AMBIENT, v);
	}
	b_bni_sret0 (vm, 5);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialDiffuse_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ox, x, 2);
	b_bni_get_param_as_natural (vm, oy, y, 3);
	b_bni_get_param_as_natural (vm, oz, z, 4);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_DIFFUSE, v);
	}
	b_bni_sret0 (vm, 5);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialSpecular_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ox, x, 2);
	b_bni_get_param_as_natural (vm, oy, y, 3);
	b_bni_get_param_as_natural (vm, oz, z, 4);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_SPECULAR, v);
	}
	b_bni_sret0 (vm, 5);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialShininess_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural_breder_lang_standard_Natural_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ox, x, 2);
	b_bni_get_param_as_natural (vm, oy, y, 3);
	b_bni_get_param_as_natural (vm, oz, z, 4);
	float v[] = {(float)x / 255, (float)y / 255, (float)z / 255, 1.0f };
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_SHININESS, v);
	}
	b_bni_sret0 (vm, 5);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialShininess_breder_lang_standard_Boolean_breder_lang_standard_Boolean_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	b_bni_get_param_as_natural (vm, ovalue, value, 2);
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glMaterialfv (face, GL_SHININESS, value);
	}
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialColorAmbientDiffuse_breder_lang_standard_Boolean_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glColorMaterial (face, GL_AMBIENT_AND_DIFFUSE);
	}
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialColorAmbient_breder_lang_standard_Boolean_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glColorMaterial (face, GL_AMBIENT);
	}
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__materialColorDiffuse_breder_lang_standard_Boolean_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_as_boolean (vm, ofront, front, 0);
	b_bni_get_param_as_boolean (vm, oback, back, 1);
	GLenum face;
	if (front && back) {
		face = GL_FRONT_AND_BACK;
	} else if (front) {
		face = GL_FRONT;
	} else if (back) {
		face = GL_BACK;
	} else {
		face = null;
	}
	if (face != null) {
		glColorMaterial (face, GL_DIFFUSE);
	}
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidCone_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Index_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, obase, base, 0);
	b_bni_get_param_as_number (vm, oheight, height, 1);
	b_bni_get_param_as_number (vm, oslices, slices, 2);
	b_bni_get_param_as_number (vm, ostacks, stacks, 3);
	glutSolidCone (base, height, slices, stacks);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireCone_breder_lang_standard_Number_breder_lang_standard_Number_breder_lang_standard_Index_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, obase, base, 0);
	b_bni_get_param_as_number (vm, oheight, height, 1);
	b_bni_get_param_as_number (vm, oslices, slices, 2);
	b_bni_get_param_as_number (vm, ostacks, stacks, 3);
	glutWireCone (base, height, slices, stacks);
	b_bni_sret0 (vm, 4);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidCube_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, height, 0);
	glutSolidCube (height);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireCube_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, height, 0);
	glutWireCube (height);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidSphere_breder_lang_standard_Number_breder_lang_standard_Index_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, radius, 0);
	b_bni_get_param_as_number (vm, oslices, slices, 1);
	b_bni_get_param_as_number (vm, ostacks, stacks, 2);
	glutSolidSphere (radius, slices, stacks);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireSphere_breder_lang_standard_Number_breder_lang_standard_Index_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, radius, 0);
	b_bni_get_param_as_number (vm, oslices, slices, 1);
	b_bni_get_param_as_number (vm, ostacks, stacks, 2);
	glutWireSphere (radius, slices, stacks);
	b_bni_sret0 (vm, 3);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidSphere_breder_lang_standard_Number_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, radius, 0);
	b_bni_get_param_as_number (vm, oslices, slices, 1);
	glutSolidSphere (radius, slices, slices);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireSphere_breder_lang_standard_Number_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, radius, 0);
	b_bni_get_param_as_number (vm, oslices, slices, 1);
	glutWireSphere (radius, slices, slices);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidTeapot_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, height, 0);
	glutSolidTeapot (height);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireTeapot_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, oheight, height, 0);
	glutWireTeapot (height);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidDodecahedron (b_vm_t* vm) {
	glutSolidDodecahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireDodecahedron (b_vm_t* vm) {
	glutWireDodecahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidIcosahedron (b_vm_t* vm) {
	glutSolidIcosahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireIcosahedron (b_vm_t* vm) {
	glutWireIcosahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidOctahedron (b_vm_t* vm) {
	glutSolidOctahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireOctahedron (b_vm_t* vm) {
	glutWireOctahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawSolidTetrahedron (b_vm_t* vm) {
	glutSolidTetrahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__drawWireTetrahedron (b_vm_t* vm) {
	glutWireTetrahedron ();
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF1 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F1 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF2 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F2 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF3 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F3 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF4 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F4 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF5 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F5 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF6 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F6 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF7 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F7 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF8 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F8 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF9 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F9 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF10 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F10 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF11 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F11 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyF12 (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_F12 + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyDown (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_DOWN + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyUp (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_UP + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyLeft (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_LEFT + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyRight (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_RIGHT + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyPageDown (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_PAGE_DOWN + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyPageUp (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_PAGE_UP + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyHome (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_HOME + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyEnd (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_END + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__getKeyInsert (b_vm_t* vm) {
	b_bni_new_onatural_define (vm, okey, GLUT_KEY_INSERT + 512);
	b_bni_sret1 (vm, 0, okey);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__isKeyPressed_breder_lang_standard_Natural (b_vm_t* vm) {
	b_bni_get_param_as_natural (vm, okey, key, 0);
	b_frame_t* frame = b_frame_get ();
	b_bni_new_oboolean_define (vm, oresult, frame->keyPressed[key] != 0);
	b_bni_sret1 (vm, 1, oresult);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__enableTexture2D (b_vm_t* vm) {
	glEnable (GL_TEXTURE_2D);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__disableTexture2D (b_vm_t* vm) {
	glDisable (GL_TEXTURE_2D);
	b_bni_sret0 (vm, 0);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__bindTexture2D_breder_lang_standard_Index (b_vm_t* vm) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	glBindTexture (GL_TEXTURE_2D, index);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__texture_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_as_number (vm, ox, x, 0);
	b_bni_get_param_as_number (vm, oy, y, 1);
	glTexCoord2d (x, y);
	b_bni_sret0 (vm, 2);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__rawImageLoad_breder_io_IFile (b_vm_t* vm) {
	b_bni_get_param_notnull (vm, ofile, 0);
	b_bni_super_object (vm, object);
	b_object_t* opath;
	if (b_bni_execute_1_return_0_param (vm, ofile, & opath, b_bni_method_iresource_getabsolutename_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	b_bni_ostring_to_text_define (vm, path, opath);
	GLuint texture = b_image_raw_load (path, 1);
	b_bni_new_oindex_define (vm, oindex, (int)texture);
	b_bni_sret1 (vm, 1, oindex);
}

b_bni_state_t breder_gui_opengl_standard_GlNative__tgaImageLoad_breder_io_IFile (b_vm_t* vm) {
	b_bni_get_param_notnull (vm, ofile, 0);
	b_bni_super_object (vm, object);
	b_object_t* opath;
	if (b_bni_execute_1_return_0_param (vm, ofile, & opath, b_bni_method_iresource_getabsolutename_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	b_bni_ostring_to_text_define (vm, path, opath);
	tga_data_t* data = b_tga_image_load (path);
	if ( ! data) {
		b_bni_throw_ioexception (vm);
	}
	GLuint texture;
	int wrap = 1;
	glGenTextures (1, & texture);
	glBindTexture (GL_TEXTURE_2D, texture);
	glTexEnvf (GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
	glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
	glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap ? GL_REPEAT : GL_CLAMP);
	glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap ? GL_REPEAT : GL_CLAMP);
	gluBuild2DMipmaps (GL_TEXTURE_2D, data->depth, data->w, data->h, data->md == 3 ? GL_RGB : GL_RGBA, GL_UNSIGNED_BYTE, data->data);
	b_bni_new_oindex_define (vm, oindex, (int)texture);
	b_bni_sret1 (vm, 1, oindex);
}
