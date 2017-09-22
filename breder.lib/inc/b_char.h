#ifndef B_CHAR_H_
#define B_CHAR_H_

#include "breder.h"

typedef char b_char_t;

#define b_achar_content(a) ((int*)a)
#define b_achar_len(a) b_aint_content(a)[0]
#define b_achar_hash(a) b_aint_content(a)[1]
#define b_achar_text(a) ((char*)(b_aint_content(a)+2))

#define b_char_compare(t1, t2) strcmp((t1), (t2))

char* b_char_new2(const char* c1, const char* c2);
char* b_char_new3(const char* c1, const char* c2, const char* c3);
char* b_char_new4(const char* c1, const char* c2, const char* c3, const char* c4);
void b_char_free(char* self);
unsigned int b_char_hash_build(const char* chars);
int b_char_size(const char*);
char* b_char_dup(const char*);
char* b_char_copy(const char* chars, int begin, int length);
int b_char_endwith(const char* self, const char* word);
char* b_char_classname_to_filename(const char* classname);
char* b_char_sub1(const char* self, int begin);
char* b_char_sub2(const char* self, int begin, int end);
void b_char_replace1(const char* self, int cold, int cnew);
char* b_char_replace2(const char* chars, int cold, int cnew);

#endif
