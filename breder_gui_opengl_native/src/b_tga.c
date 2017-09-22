#include "native.h"

tga_data_t* b_tga_image_load (char* fn) {
	FILE* fh = NULL;
	fh = fopen (fn, "rb");
	if (fh == NULL) {
		return 0;
	}
	tga_data_t* tga = b_memory_alloc_typed (tga_data_t, 1);
	if ( ! tga) {
		fclose (fh);
		return 0;
	}
	{
		fseek (fh, 12, SEEK_SET);
		fread ( & tga->w, sizeof(short int), 1, fh);
		fseek (fh, 14, SEEK_SET);
		fread ( & tga->h, sizeof(short int), 1, fh);
		fseek (fh, 16, SEEK_SET);
		fread ( & tga->depth, sizeof(short int), 1, fh);
	}
	{
		int md = tga->depth / 8;
		int t = tga->h * tga->w * md;
		tga->data = b_memory_alloc_typed(unsigned char, t);
		if ( ! tga->data) {
			free (tga);
			fclose (fh);
			return 0;
		}
		fseek (fh, 18, SEEK_SET);
		fread (tga->data, sizeof(unsigned char), t, fh);
		fclose (fh);
		if (md >= 3) {
			unsigned char aux;
			int i;
			for (i = 0; i < t; i += md) {
				aux = tga->data[i];
				tga->data[i] = tga->data[i + 2];
				tga->data[i + 2] = aux;
			}
		}
	}
	return tga;
}
