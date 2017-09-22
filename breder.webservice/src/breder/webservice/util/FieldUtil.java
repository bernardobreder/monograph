/**
 * 
 */

package breder.webservice.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bbreder
 */
public class FieldUtil {

	public static Field[] getAllFields(Class<?> begin, Class<?> end) {
		Class<?> c = end;
		List<Field> fields = new ArrayList<Field>();
		{
			while (!c.equals(begin)) {
				for (Field field : c.getDeclaredFields()) {
					field.setAccessible(true);
					fields.add(field);
				}
				c = c.getSuperclass();
			}
		}
		return fields.toArray(new Field[0]);
	}

}
