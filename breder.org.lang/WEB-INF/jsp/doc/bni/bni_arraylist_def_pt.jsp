<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos tópicos anteriores, foram discutidos diversas técnicas de se trabalhar com operações nativas.  
A seguir, será mostrado a implementação da classe <code>breder.util.ArrayList</code> na Linguagem Breder.
</p>

<pre>
package breder.util.standard;
public class ArrayList<E> implements IList<E> {
  public native ArrayList();
  public native void add(notnull E value);
  public native notnull E get(notnull Index index);
  public native void set(notnull Index index, notnull E value);
  public native final notnull Natural size();
  public native void finalize() ;
  public native notnull Boolean isEmpty() ;
  public native notnull Boolean contain(notnull E element) ;
  public native Index indexOf(notnull E element) ;
  public native Index indexOf(notnull E element, notnull Index startIndex) ;
  public native notnull E remove(notnull E element) ;
  public native notnull E remove(notnull Index index) ;
  public native void clear() ;
}
</pre>

<p>
A seguir, será mostrado a implementação da classe <code>breder.util.ArrayList</code> nativamente.
</p>

<pre>
#include "b_bni.h"

b_bni_state_t breder_util_standard_ArrayList$ArrayList
    (b_vm_t* vm, b_object_t* object) {
  b_bni_super_object(vm, object);
  b_bni_set_data_current(vm, object, b_array_new());
  b_bni_cret(vm, 0);
}

b_bni_state_t breder_util_standard_ArrayList$add$breder_lang_Object
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_nullable(vm, ovalue, 0);
  if (ovalue) {
  b_bni_inc_used(vm, ovalue);
  }
  b_array_add(data, ovalue);
  b_bni_ret0(vm, 1);
}

b_bni_state_t breder_util_standard_ArrayList$get$breder_lang_Index
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_as_index(vm, oindex, index, 0);
  int size = b_array_size_safely(data);
  b_bni_test_index(vm, index <= 0 || index > size);
  b_object_t* oreturn = b_array_get_typed(b_object_t, data, index - 1);
  b_bni_ret1(vm, 1, oreturn);
}

b_bni_state_t 
    breder_util_standard_ArrayList$set$breder_lang_Index$breder_lang_Object
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_as_index(vm, oindex, index, 0);
  b_bni_get_param_nullable(vm, ovalue, 1);
  int size = b_array_size_safely(data);
  b_bni_test_index(vm, index <= 0 || index > size);
  b_object_t* ooldvalue = b_array_get_typed(b_object_t, data, index - 1);
  b_bni_dec_used(vm, ooldvalue);
  b_array_set(data, (int) index - 1, ovalue);
  b_bni_inc_used(vm, ovalue);
  b_bni_ret0(vm, 2);
}

b_bni_state_t breder_util_standard_ArrayList$size
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_new_onumber_define(vm, oreturn, b_array_size(data));
  b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$isEmpty
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  int size = b_array_size_safely(data);
  b_bni_new_oboolean_define(vm, oreturn, size == 0);
  b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$contain$breder_lang_Object
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_notnull(vm, oparam, 0);
  int n, size = b_array_size_safely(data);
  for (n = 0; n < size; n++) {
    b_object_t* oitem = b_array_get_typed(b_object_t, data, n);
    b_bni_execute_1_return_1_param(vm, oequal, oparam,
      b_bni_method_object$operatorequal_id(vm), oitem);
    if (b_bni_oboolean_to_primitive(vm, oequal)) {
      b_object_t* oreturn = b_bni_new_otrue(vm);
      b_bni_ret1(vm, 1, oreturn);
    }
  }
  b_object_t* oreturn = b_bni_new_ofalse(vm);
  b_bni_ret1(vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$indexOf$breder_lang_Object
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_notnull(vm, oparam, 0);
  int n, size = b_array_size_safely(data);
  for (n = 0; n < size; n++) {
    b_object_t* oitem = b_array_get_typed(b_object_t, data, n);
    b_bni_execute_1_return_1_param(vm, oequal, oparam,
        b_bni_method_object$operatorequal_id(vm), oitem);
    if (b_bni_oboolean_to_primitive(vm, oequal)) {
      b_bni_new_onumber_define(vm, oreturn, n + 1);
      b_bni_ret1(vm, 1, oreturn);
    }
  }
  b_object_t* oreturn = b_bni_null(vm);
  b_bni_ret1(vm, 1, oreturn);
}

b_bni_state_t 
    breder_util_standard_ArrayList$indexOf$breder_lang_Object$breder_lang_Index
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_notnull(vm, oparam, 0);
  b_bni_get_param_as_index(vm, oindex, index, 1);
  int n, size = b_array_size_safely(data);
  for (n = index - 1; n < size; n++) {
    b_object_t* oitem = b_array_get_typed(b_object_t, data, n);
    b_bni_execute_1_return_1_param(vm, oequal, oparam,
        b_bni_method_object$operatorequal_id(vm), oitem);
    if (b_bni_oboolean_to_primitive(vm, oequal)) {
      b_bni_new_onumber_define(vm, oreturn, n + 1);
      b_bni_ret1(vm, 2, oreturn);
    }
  }
  b_object_t* oreturn = b_bni_null(vm);
  b_bni_ret1(vm, 2, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$remove$breder_lang_Object
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_notnull(vm, oparam, 0);
  int n, size = b_array_size_safely(data);
  for (n = 0; n < size; n++) {
    b_object_t* oreturn = b_array_get_typed(b_object_t, data, n);
    b_bni_execute_1_return_1_param(vm, oequal, oparam,
        b_bni_method_object$operatorequal_id(vm), oreturn);
    if (b_bni_oboolean_to_primitive(vm, oequal)) {
      b_array_remove(data, n);
      b_bni_dec_used(vm, oreturn);
      b_bni_ret1(vm, 1, oreturn);
    }
  }
  b_object_t* oreturn = b_bni_null(vm);
  b_bni_ret1(vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$remove$breder_lang_Index
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_get_param_as_index(vm, oindex, index, 0);
  int size = b_array_size_safely(data);
  b_bni_test_index(vm, index <= 0 || index > size);
  b_object_t* oreturn = b_array_get_typed(b_object_t, data, index - 1);
  b_array_remove(data, index - 1);
  b_bni_dec_used(vm, oreturn);
  b_bni_ret1(vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList$clear
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_array_clean(data);
  b_bni_ret0(vm, 0);
}

b_bni_state_t breder_util_standard_ArrayList$finalize
    (b_vm_t* vm, b_object_t* object, b_array_t* data) {
  b_bni_super_object_finalize(vm, object);
  int n, size = b_array_size(data);
  for (n = 0; n < size; n++) {
    b_object_t* object = b_array_get_typed(b_object_t, data, n);
    b_bni_dec_used(vm, object);
  }
  b_array_free(data);
  b_bni_ret0(vm, 0);
}
</pre>