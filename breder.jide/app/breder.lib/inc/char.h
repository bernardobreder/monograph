#ifndef B_CHARS_H_
#define B_CHARS_H_

#include "breder.h"

typedef char b_char_t;

struct b_chars_t {
	int hash;
	int size;
	char* chars;
};

b_chars_t* b_chars_new(const char* c);

b_chars_t* b_chars_new1(char* c);

int b_chars_size(b_chars_t* c);

int b_chars_hash(b_chars_t* c);

const char* b_chars_text(b_chars_t* c);

int b_chars_build_hash_t(const char* self);

int b_chars_end_with(char* self, char* word);

char* b_char_new2(const char*, const char*);

char* b_char_new3(const char*, const char*, const char*);

char* b_char_new4(const char* c1, const char* c2, const char* c3,
		const char* c4);

char* b_char_dup(const char* chars);

int b_char_compare(b_char_t*, b_char_t*);

char* b_char_copy(const char* chars, int begin, int length);

char* b_char_replace2(char* chars, int old, int new);

void b_char_free(char*);

int b_char_endwith(char* self, char* word);

b_char_t* b_char_classname_to_filename(const b_char_t* classname);

b_char_t* b_char_sub1(b_char_t* self, int begin);

b_char_t* b_char_sub2(b_char_t* self, int begin, int end);

void b_char_replace1(b_char_t* self, int old, int new);

int b_char_size(b_char_t* self);

unsigned int b_char_build_hash_t(b_char_t* chars);

#endif
