
package breder.compiler.util;

import java.util.List;

import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BType;

public class ConvertUtil {

	public static List<BType> convertParamsToTypes(List<BParam> params) {
		List<BType> types = new LightArrayList<BType>();
		for (BParam param : params) {
			types.add(param.getType());
		}
		return types;
	}

	public static List<BType> convertRValuesToTypes(Context context, List<IRValue> rvalues) {
		List<BType> types = new LightArrayList<BType>();
		for (IRValue rvalue : rvalues) {
			if (rvalue.getTypes().length == 0) {
				throw new BrederJRuntimeException(context, rvalue.getToken(), "rvalue with no return");
			}
			types.add(rvalue.getType());
		}
		return types;
	}
}
