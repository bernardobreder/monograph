#ifndef B_TGA_H_
#define B_TGA_H_

typedef struct tga_data_t tga_data_t;

struct tga_data_t {
	short int depth;
	int w, h;
	unsigned char* data;
	int md;
};

tga_data_t* b_tga_image_load(char* fn);

#endif /* B_TGA_H_ */
