#include <stdio.h>
#include "lightning.h"

static jit_insn codeBuffer[1024];

typedef int (*pifi)(int); /* Pointer to Int Function of Int */

int main() {
	pifi nfibs = (pifi) (jit_set_ip(codeBuffer).iptr);
	int in; /* offset of the argument */
	jit_insn *ref; /* to patch the forward reference */
	jit_insn *loop; /* start of the loop */

	jit_leaf (1);
	in = jit_arg_ui ();
	jit_getarg_ui(JIT_R2, in); /* R2 = n */
	jit_movi_ui (JIT_R1, 1);
	ref = jit_blti_ui (jit_forward(), JIT_R2, 2);
	jit_subi_ui (JIT_R2, JIT_R2, 1);
	jit_movi_ui (JIT_R0, 1);

	loop = jit_get_label();
	jit_subi_ui (JIT_R2, JIT_R2, 1); /* decr. counter */
	jit_addr_ui (JIT_V0, JIT_R0, JIT_R1); /* V0 = R0 + R1 */
	jit_movr_ui (JIT_R0, JIT_R1); /* R0 = R1 */
	jit_addi_ui (JIT_R1, JIT_V0, 1); /* R1 = V0 + 1 */
	jit_bnei_ui (loop, JIT_R2, 0); /* if (R2) goto loop; */

	jit_patch(ref); /* patch forward jump */
	jit_movr_ui (JIT_RET, JIT_R1); /* RET = R1 */
	jit_ret ();

	/* call the generated code, passing 36 as an argument */
	jit_flush_code(codeBuffer, jit_get_ip().ptr);
	printf("nfibs(%d) = %d", 36, nfibs(5));
	return 0;
}
