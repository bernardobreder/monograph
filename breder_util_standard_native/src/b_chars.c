#include "breder.h"
#include "b_chars.h"

b_chars_t* b_chars_new1 (char* c) {
	b_chars_t* chars = b_memory_alloc_typed (b_chars_t, 1);
	chars->size = - 1;
	chars->chars = c;
	return chars;
}

b_chars_t* b_chars_new (const char* c) {
	return b_chars_new1 (b_char_dup (c));
}

void b_chars_free (b_chars_t* self) {
	b_char_free (self->chars);
	b_memory_free (self);
}

int b_chars_size (b_chars_t* c) {
	if (c->size < 0) {
		c->size = strlen (c->chars);
	}
	return c->size;
}

const char* b_chars_text (b_chars_t* c) {
	return c->chars;
}

unsigned int b_chars_hash (b_chars_t* c) {
	if ( ! c->hash) {
		c->hash = b_char_hash_build (c->chars);
	}
	return c->hash;
}
