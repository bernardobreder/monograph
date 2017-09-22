<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><h2>
    b_bni_class_define ( void* class_name , void* field_name , const char* class_fullname ) 
</h2><dl><dt>Describer :</dt><dd>
	Define a class, which will be in cache iwth function named by "class_name".
</dd><dt>Parameter :</dt><dd>
	void* class_name - Name of the class which want to create.
</dd><dd>
	void* field_name - Name of the field which want to cache.
</dd><dd>
	const char* class_fullname - Complete name of the class, incluing all parameters and returns.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_method_define ( void* method_name , void* field_name , b_class_id_t class_index , const char* method_fullname ) 
</h2><dl><dt>Describer :</dt><dd>
    Define a class, which will be in cache iwth function named by "class_name".
</dd><dt>Parameter :</dt><dd>
	void* method_name - Name of the method which want to create.
</dd><dd>
	void* field_name - Name of the field which want to cache.
</dd><dd>
	b_class_id_t class_index - Code of the class which has this method.
</dd><dd>
	const char* method_fullname - Complete name of the class, incluing all parameters and returns.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_cret ( b_vm_t* vm , int params ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the constructor method.
	All native constructor must be finalize with this method.
	The target of this function is to finish the method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current constructor.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_sret0 ( b_vm_t* vm , int params ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the static method.
	All native static method must be finalize with this method.
	The target of this function is to finish the static method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current static method.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_ret0 ( b_vm_t* vm , int params ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the method.
	All native method must be finalize with this method.
	The target of this function is to finish the method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current method.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_sret1 ( b_vm_t* vm , int params , b_object_t* return_object ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the static method.
	All native static method must be finalize with this method.
	The target of this function is to finish the static method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current static method.
</dd><dd>
	b_object_t* return_object - Object which want to return.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_ret1 ( b_vm_t* vm , int params , b_object_t* return_object ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the method.
	All native method must be finalize with this method.
	The target of this function is to finish the method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current method.
</dd><dd>
	b_object_t* return_object - Object which want to return.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_sret2 ( b_vm_t* vm , int params , b_object_t* return_object_a , b_object_t* return_object_b ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the static method.
	All native static method must be finalize with this method.
	The target of this function is to finish the static method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current static method.
</dd><dd>
	b_object_t* return_object_a - First object which want to return.
</dd><dd>
	b_object_t* return_object_b - Second object which want to return.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_ret2 ( b_vm_t* vm , int params , b_object_t* return_object_a , b_object_t* return_object_b ) 
</h2><dl><dt>Describer :</dt><dd>
	Define the end of the method.
	All native method must be finalize with this method.
	The target of this function is to finish the method and pop same number of object from object stack.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	int params - Number of parameter from the current method.
</dd><dd>
	b_object_t* return_object_a - First object which want to return.
</dd><dd>
	b_object_t* return_object_b - Second object which want to return.
</dd><dt>Return :</dt><dd>
</dd></dl></p>

<p><h2>
    b_bni_state_t b_bni_execute_0_return_0_param ( b_vm_t* vm , b_object_t* object , b_method_id_t method_index ) 
</h2><dl><dt>Describer :</dt><dd>
	This function will call a method which return zero object and has zero parameter.
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	b_object_t* object - Object of the method.
</dd><dd>
	b_method_id_t method_index - Code of the method.
</dd><dt>Return :</dt><dd>
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd></dl></p>

<p><h2>
    b_bni_state_t b_bni_execute_1_return_0_param ( b_vm_t* vm , b_object_t* object , b_object_t** object_return , b_method_id_t method_index ) 
</h2><dl><dt>Describer :</dt><dd>
	This function will call a method which return one object and has zero parameter.
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	b_object_t* object - Object of the method.
</dd><dd>
	b_object_t** object_return - Object returned by the method.
</dd><dd>
	b_method_id_t method_index - Code of the method.
</dd><dt>Return :</dt><dd>
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd></dl></p>

<p><h2>
    b_bni_state_t b_bni_execute_0_return_1_param ( b_vm_t* vm , b_object_t* object , b_method_id_t method_index , b_object_t* object_param ) 
</h2><dl><dt>Describer :</dt><dd>
	This function will call a method which return one object and has zero parameter.
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	b_object_t* object - Object of the method.
</dd><dd>
	b_method_id_t method_index - Code of the method.
</dd><dd>
	b_object_t* object_param - Object to be passed as parameter.
</dd><dt>Return :</dt><dd>
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd></dl></p>

<p><h2>
    b_bni_state_t b_bni_execute_1_return_1_param ( b_vm_t* vm , b_object_t* object , b_object_t** object_return , b_method_id_t method_index , b_object_t* object_param ) 
</h2><dl><dt>Describer :</dt><dd>
	This function will call a method which return one object and has zero parameter.
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd><dt>Parameter :</dt><dd>
	b_vm_t* vm - Reference of Virtual Machine.
</dd><dd>
	b_object_t* object - Object of the method.
</dd><dd>
	b_object_t** object_return - Object returned by the method.
</dd><dd>
	b_method_id_t method_index - Code of the method.
</dd><dd>
	b_object_t* object_param - Object to be passed as parameter.
</dd><dt>Return :</dt><dd>
	If an error occurs in this function, will return B_BNI_FAIL.
	If this function completed successfully without error, this function will return B_BNI_SUCESS.
</dd></dl></p>
