#include "breder.h"

char* b_char_new2 (const char* c1, const char* c2) {
	int l1 = b_char_size (c1);
	int l2 = b_char_size (c2);
	char* chars = (char*)b_memory_alloc0 ( (l1 + l2 + 1) * sizeof(char));
	strcpy ( chars , c1 );
	strcpy ( & chars [ l1 ] , c2 );
	char* aux = b_char_dup (chars);
	b_char_free (chars);
	return aux;
}

char* b_char_new3 (const char* c1, const char* c2, const char* c3) {
	int l1 = b_char_size (c1);
	int l2 = b_char_size (c2);
	int l3 = b_char_size (c3);
	char* chars = (char*)b_memory_alloc0 ( (l1 + l2 + l3 + 1) * sizeof(char));
	strcat ( chars , c1 );
	strcat ( chars , c2 );
	strcat ( chars , c3 );
	char* aux = b_char_dup (chars);
	b_char_free (chars);
	return aux;
}

char* b_char_new4 (const char* c1, const char* c2, const char* c3, const char* c4) {
	int l1 = b_char_size (c1);
	int l2 = b_char_size (c2);
	int l3 = b_char_size (c3);
	int l4 = b_char_size (c4);
	char * chars = (char*)b_memory_alloc0 ( (l1 + l2 + l3 + l4 + 1) * sizeof(char));
	strcat ( chars , c1 );
	strcat ( chars , c2 );
	strcat ( chars , c3 );
	strcat ( chars , c4 );
	char* aux = b_char_dup (chars);
	b_char_free (chars);
	return aux;
}

void b_char_free (char* self) {
	b_memory_free (self);
}

unsigned int b_char_hash_build (const char* chars) {
	unsigned int hash = 1;
	int n = 0, len = b_char_size (chars);
	for (n = len; chars[0]; hash += ( * chars ++ * 31 ^ (n - 1)))
		;
	return hash;
}

int b_char_size (const char* self) {
	return strlen (self);
}
char* b_char_dup (const char* chars) {
	return strdup (chars);
}

char* b_char_copy (const char* chars, int begin, int length) {
	char* result = (char*)b_memory_alloc0 ( (length + 1) * sizeof(char));
	strncpy ( result , chars + begin , length + 1 );
	result[length] = 0;
	char* aux = b_char_dup (result);
	b_char_free (result);
	return aux;
}

int b_char_endwith (const char* self, const char* word) {
	int slen = b_char_size (self);
	int wlen = b_char_size (word);
	self = & self[slen - wlen];
	return ! b_char_compare(self, word);
}

char* b_char_classname_to_filename (const char* classname) {
	char* chars = b_char_new2 (classname, ".breder");
	char* index = strchr (chars, '.');
	char* index2;
	while (index) {
		index2 = index;
		* index = '/';
		index = strchr (chars, '.');
		if ( ! index) * index2 = '.';
	}
	return chars;
}

char* b_char_sub1 (const char* self, int begin) {
	return b_char_dup (self + begin);
}

char* b_char_sub2 (const char* self, int begin, int end) {
	char* text = (char*)self;
	char aux = self[end];
	text[end] = 0;
	char* chars = b_char_dup (self + begin);
	text[end] = aux;
	return chars;
}

void b_char_replace1 (const char* self, int cold, int cnew) {
	char* index = strchr (self, cold);
	while (index) {
		* index = cnew;
		index = strchr (self, cold);
	}
}

char* b_char_replace2 (const char* chars, int cold, int cnew) {
	char* cpy = b_char_dup (chars);
	char* aux = cpy;
	while ( (aux = strchr (aux, cold)))
		* aux = cnew;
	return cpy;
}

