#ifndef B_LINKER_H_
#define B_LINKER_H_

#include "breder.h"

struct b_linker_t {
	b_linker_node_t* current;
	b_linker_node_t* first;
	b_linker_node_t* last;
	int count;
};

struct b_linker_node_t {
	b_linker_node_t *back;
	b_linker_node_t *next;
	void *data;
};

#define b_linker_begin( Linker ) ((Linker)->current = (Linker)->first)
#define b_linker_end( Linker ) ((Linker)->current = (Linker)->last)
#define b_linker_current( Linker ) (Linker)->current->data
#define b_linker_current_typed( Type , Linker ) ( Type* )b_linker_current( Linker )
#define b_linker_has_current( Linker ) (Linker)->current
#define b_linker_remove_current(Linker) b_linker_remove_node(Linker, (Linker)->current)
#define b_linker_next( Linker ) ((Linker)->current = (Linker)->current->next)
#define b_linker_back( Linker ) ((Linker)->current = (Linker)->current->back)

b_linker_t* b_linker_new();

void b_linker_free(b_linker_t *self);

#define b_linker_free1(SELF, TYPE, FUNC) \
		{ \
			struct b_linker_node_t* node = (SELF)->first; \
			while (node) { \
				if (node->data) { \
					FUNC((TYPE)node->data); \
				} \
				b_memory_free(node); \
				node = node->next; \
			} \
			b_memory_free((SELF)); \
		}

int b_linker_add_first(b_linker_t *self, b_vm_t* vm, void* data);

int b_linker_add_last(b_linker_t *self, b_vm_t* vm, void* data);

void* b_linker_get_first(b_linker_t* self);

void* b_linker_get_last(b_linker_t* self);

void b_linker_remove_node(b_linker_t* self, b_linker_node_t* node);

#define b_linker_size(LINKER) \
		((LINKER)->count)

#endif
