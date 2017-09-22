#ifndef B_HASH_H_
#define B_HASH_H_

#include "breder.h"

#define B_HASH_SIZE 15

struct b_hashentity_t {
	unsigned int hash;
	unsigned int size;
	char* key;
	void* value;
};

struct b_hashnew_t {
	int structType;
	int size;
	b_array_t* entitys;
	void* freeFunc;
	int isDupKey;
};

struct b_hash_key_t {
	int key1;
	int key2;
};

#define b_hashp_new( FreeFunc ) b_hash_new( sizeof(void*) , FreeFunc )

#define b_hashp_new1( FreeFunc , isDupKey ) b_hash_new1( sizeof(void*) , FreeFunc , isDupKey )

b_hashnew_t* b_hash_new1(int size, void* freeFunc, int isDupKey);

b_hashnew_t* b_hash_new(int size, void* freeFunc);

b_hashnew_t* b_hash_new2();

#define b_hashnew_get_typed( type , hash , key ) ((type*)b_hashnew_get(hash,key))

void* b_hashnew_put1(b_hashnew_t* self, const char* key, void* value);

void* b_hashnew_put2(b_hashnew_t* self, struct b_chars_t* key, void* value);

void* b_hashnew_put(b_hashnew_t* self, const char* key, unsigned int hash, int size, void* value);

int b_hashnew_size(b_hashnew_t* self);

void* b_hashnew_get1(b_hashnew_t* self, char* key);

void* b_hashnew_get(b_hashnew_t* self, const char* key, unsigned int hash);

void* b_hashnew_get2(b_hashnew_t* self, struct b_chars_t* key);

void b_hashnew_free1(b_hashnew_t* self, void* func);

void b_hashnew_free(b_hashnew_t* self);

void** b_hashnew_toarray(b_hashnew_t* self);

int b_hashp_get_index(b_hashnew_t* self, char* key);

void** b_hashnew_close(b_hashnew_t* self);

void* b_hashclose_get(b_hashclose_t* self, const char* key);

void* b_hashclose_get1(b_hashclose_t* self, struct b_chars_t* key);

void* b_hashclose_put1(b_hashclose_t* self, struct b_chars_t* key, void* value);

void b_hashclose_free(b_hashclose_t* self);

#define b_hashclose_entitys( HASHMAP ) (((int*)HASHMAP)[0])
#define b_hashclose_entity_length( HASHMAP ) (((int*)HASHMAP)[1])
#define b_hashclose_sizeof( HASHCLOSE ) (b_hashclose_entitys(HASHCLOSE)*b_hashclose_entity_length(HASHCLOSE)*sizeof(b_hashentity_t)+2*sizeof(int))
#define b_hashclose_content( HASHMAP ) ((b_hashentity_t*)(((int*)HASHMAP)+2))
#define b_hashclose_array_index( HASHMAP , HASHCODE ) (HASHCODE % b_hashclose_entitys(HASHMAP))
#define b_hashclose_begin( HASHMAP , HASHCODE ) (b_hashentity_t*)(b_hashclose_content(HASHMAP) + b_hashclose_array_index(HASHMAP,HASHCODE) * b_hashclose_entity_length(HASHMAP));

#endif /* B_HASH_H_ */
