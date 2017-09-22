#include "breder.h"

b_linker_t* b_linker_new() {
	b_linker_t* self = calloc(1, sizeof(b_linker_t));
	return self;
}

void b_linker_free(b_linker_t* self) {
	b_linker_node_t* node = self->first;
	while (node) {
		b_memory_free(node);
		node = node->next;
	}
	b_memory_free(self);
}

void b_linker_free1(b_linker_t* self, void* freeFunc) {
	b_linker_node_t* node = self->first;
	while (node) {
		if (freeFunc && node->data) {
			b_call_function(void,freeFunc,(void*))(node->data);
		}
		b_memory_free(node);
		node = node->next;
	}
	b_memory_free(self);
}

static b_linker_node_t* b_linker_node_new(b_vm_t* vm, b_linker_node_t *back, b_linker_node_t *next, void* data) {
	b_linker_node_t* node = b_memory_alloc(vm, sizeof(b_linker_node_t));
	node->back = back;
	node->next = next;
	node->data = data;
	return node;
}

int b_linker_add_first(b_linker_t *self, b_vm_t* vm, void* data) {
	if (!self->count) {
		b_linker_node_t* node = b_linker_node_new(vm, 0, 0, data);
		if (node == NULL) {
			return B_BNI_FAIL;
		}
		self ->last = self->first = node;
	} else {
		b_linker_node_t* node = b_linker_node_new(vm, 0, self->first, data);
		if (node == NULL) {
			return B_BNI_FAIL;
		}
		self->first = node;
		self->first->next->back = self->first;
	}
	++self->count;
	return B_BNI_SUCCESS;
}

int b_linker_add_last(b_linker_t *self, b_vm_t* vm, void* data) {
	if (!self->count) {
		b_linker_node_t * node = b_linker_node_new(vm, 0, 0, data);
		if (node == NULL) {
			return B_BNI_FAIL;
		}
		self->last = self->first = node;
	} else {
		b_linker_node_t* node = b_linker_node_new(vm, self->last, 0, data);
		if (node == NULL) {
			return B_BNI_FAIL;
		}
		self->last = node;
		self->last->back->next = self->last;
	}
	++self->count;
	return B_BNI_SUCCESS;
}

void* b_linker_get_first(b_linker_t* self) {
	if (self->first)
		return self->first->data;
	else
		return 0;
}

void* b_linker_get_last(b_linker_t* self) {
	if (self->last)
		return self->last->data;
	else
		return 0;
}

void b_linker_remove_node(b_linker_t* self, b_linker_node_t* node) {
	if (node->back) {
		node->back->next = node->next;
		if (node->next)
			node->next->back = node->back;
		else
			self->last = node->back;
	} else {
		if (node->next) {
			node->next->back = 0;
			self->first = node->next;
		} else {
			self->first = self->last = 0;
		}
	}
	if (self->current == node) {
		self->current = node->next;
	}
	b_memory_free(node);
	--self->count;
}

int b_linker_size(b_linker_t *self) {
	return self->count;
}

