#include "breder.h"

int b_chars_build_hash_t(const char* chars) {
	int hash = 1;
	int n = 0, len = strlen(chars);
	for (n = len; chars[0]; hash += (*chars++ * 31 ^ (n - 1)))
		;
	return hash;
}

int b_chars_end_with(char* self, char* word) {
	int slen = strlen(self);
	int wlen = strlen(word);
	self = &self[slen - wlen];
	return !strcmp(self, word);
}

b_chars_t* b_chars_new(const char* c) {
	return b_chars_new1(b_char_dup(c));
}

b_chars_t* b_chars_new1(char* c) {
	b_chars_t* chars = calloc(1, sizeof(b_chars_t));
	chars->size = -1;
	chars->chars = c;
	return chars;
}

int b_chars_size(b_chars_t* c) {
	if (c->size == -1) {
		c->size = strlen(c->chars);
	}
	return c->size;
}

int b_chars_hash(b_chars_t* c) {
	if (!c->hash) {
		c->hash = b_chars_build_hash_t(c->chars);
	}
	return c->hash;
}

const char* b_chars_text(b_chars_t* c) {
	return c->chars;
}

char* b_char_new2(const char* c1, const char* c2) {
	int l1 = strlen(c1);
	int l2 = strlen(c2);
	char* chars = calloc(l1 + l2 + 1, sizeof(char));
	strcpy ( chars , c1 );
	strcpy ( & chars [ l1 ] , c2 );
	char* aux = b_char_dup(chars);
	b_char_free(chars);
	return aux;
}

char* b_char_new3(const char* c1, const char* c2, const char* c3) {
	int l1 = strlen(c1);
	int l2 = strlen(c2);
	int l3 = strlen(c3);
	char* chars = calloc(l1 + l2 + l3 + 1, sizeof(char));
	strcat ( chars , c1 );
	strcat ( chars , c2 );
	strcat ( chars , c3 );
	char* aux = b_char_dup(chars);
	b_char_free(chars);
	return aux;
}

char* b_char_new4(const char* c1, const char* c2, const char* c3,
		const char* c4) {
	int l1 = strlen(c1);
	int l2 = strlen(c2);
	int l3 = strlen(c3);
	int l4 = strlen(c4);
	char * chars = calloc(l1 + l2 + l3 + l4 + 1, sizeof(char));
	strcat ( chars , c1 );
	strcat ( chars , c2 );
	strcat ( chars , c3 );
	strcat ( chars , c4 );
	char* aux = b_char_dup(chars);
	b_char_free(chars);
	return aux;
}

char* b_char_dup(const char* chars) {
	char* aux = calloc(strlen(chars) + 1, sizeof(char));
	strcpy ( aux , chars );
	return aux;
}

int b_char_compare(b_char_t* self, b_char_t* other) {
	return strcmp(self, other);
}

char* b_char_copy(const char* chars, int begin, int length) {
	char* result = calloc(length + 1, sizeof(char));
	strncpy ( result , chars + begin , length + 1 );
	result[length] = 0;
	char* aux = b_char_dup(result);
	b_char_free(result);
	return aux;
}

char* b_char_replace2(char* chars, int old, int new) {
	char* cpy = b_char_dup(chars);
	char* aux = cpy;
	while ((aux = strchr(aux, old)))
		*aux = new;
	return cpy;
}

void b_char_free(char* self) {
	free(self);
}

int b_char_endwith(char* self, char* word) {
	int slen = strlen(self);
	int wlen = strlen(word);
	self = &self[slen - wlen];
	return !strcmp(self, word);
}

unsigned int b_char_build_hash_t(b_char_t* chars) {
	unsigned int hash = 1;
	int n = 0, len = strlen(chars);
	for (n = len; chars[0]; hash += (*chars++ * 31 ^ (n - 1)))
		;
	return hash;
}

b_char_t* b_char_classname_to_filename(const b_char_t* classname) {
	b_char_t* chars = b_char_new2(classname, ".breder");
	char* index = strchr(chars, '.');
	char* index2;
	while (index) {
		index2 = index;
		*index = '/';
		index = strchr(chars, '.');
		if (!index)
			*index2 = '.';
	}
	return chars;
}

b_char_t* b_char_sub1(b_char_t* self, int begin) {
	return b_char_dup(self + begin);
}

b_char_t* b_char_sub2(b_char_t* self, int begin, int end) {
	char aux = self[end];
	self[end] = 0;
	b_char_t* chars = b_char_dup(self + begin);
	self[end] = aux;
	return chars;
}

void b_char_replace1(b_char_t* self, int old, int new) {
	char* index = strchr(self, old);
	while (index) {
		*index = new;
		index = strchr(self, old);
	}
}

int b_char_size(b_char_t* self) {
	return strlen(self);
}
