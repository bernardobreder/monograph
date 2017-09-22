#ifndef B_CHARS_H_
#define B_CHARS_H_

struct b_chars_t {
	unsigned int hash;
	int size;
	char* chars;
};

b_chars_t* b_chars_new(const char* c);

b_chars_t* b_chars_new1(char* c);

void b_chars_free(b_chars_t* self);

int b_chars_size(b_chars_t* c);

const char* b_chars_text(b_chars_t* c);

unsigned int b_chars_hash(b_chars_t* c);

#endif
