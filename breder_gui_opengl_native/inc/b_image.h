#ifndef PNGLOAD_H
#define PNGLOAD_H

int b_image_png_load(char *file, unsigned long *pwidth, unsigned long *pheight,
		char **image_data_ptr);

b_state_t b_image_tga_load(char *fileName, TGAFILE *tgaFile);

#endif /* PNGLOAD_H */
