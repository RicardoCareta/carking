package com.pds.carking.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapOperations {
	
	public static final Map<String, Object> convertToMap (Object instanceClass) {
		Map<String, Object> mapReturn = new LinkedHashMap<>();
		List<Field> fields = getAllFields(new LinkedList<Field>(), instanceClass.getClass());
		
		for(Field f : fields) {
			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(instanceClass);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			mapReturn.put(f.getName(), value);
		}
		
		return mapReturn;
	}
	
	private static List<Field> getAllFields (List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		
		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}
		return fields;
	}
}
