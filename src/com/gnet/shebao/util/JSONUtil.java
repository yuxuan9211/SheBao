/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file JSONUtil.java
 * @author wenhui.li
 * @date 2012-9-26 下午8:00:22 
 * Revision History 
 *     - 2012-9-26: change content for what reason
 ****************************************************************************************/
package com.gnet.shebao.util;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @brief JSON操作的辅助类
 * 
 *  功能描述： 
 *  -# JSON格式转换成JSONObject
 *  -# JSON格式和Map
 *  -# Map转换为JSON
 *  -# String格式转换为JSON
 *  -# Boolean转换为JSON
 *  -# int转换为JSON
 */
public  class JSONUtil {
	
	
	/**
	 * @brief 将 Map<String, String> 转换为 JSON
	 * @param map 要转换的Map
	 * @return 转换后的Sting
	 */
	public static String map2Json(Map<String, Object> map) 
	{
		if (map.isEmpty()) 
		{
			return "{}";
		}
		final StringBuilder sb = new StringBuilder(map.size() << 4); // 4次方
		sb.append('{');
		final Set<String> keys = map.keySet();
		for (final String key : keys) 
		{
			final Object value = map.get(key);
			sb.append('\"');
			sb.append(key); // 不能包含特殊字符
			sb.append('\"');
			sb.append(':');
			sb.append(toJson(value)); // 循环引用的对象会引发无限递归
			sb.append(',');
		}
		// 将最后的 ',' 变为 '}':
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}
	
	/**
	 * @brief 将 Map<String, Object> 转换为 JSON
	 * @param map 要转换的Map
	 * @return 转换后的Sting
	 */
	public static String objectMap2Json(Map<String, Object> map)
	{
		if (map.isEmpty()) 
		{
			return "{}";
		}
		final StringBuilder sb = new StringBuilder(map.size() << 4); // 4次方
		sb.append('{');
		final Set<String> keys = map.keySet();
		for (final String key : keys)
		{
			final Object value = map.get(key);
			sb.append('\"');
			sb.append(key); // 不能包含特殊字符
			sb.append('\"');
			sb.append(':');
			sb.append(toJson(value)); // 循环引用的对象会引发无限递归
			sb.append(',');
		}
		// 将最后的 ',' 变为 '}':
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}
	
	/**
	 * @brief 将JSON格式的字串转换为Map
	 * @param jsonString 要转换的String
	 * @return 转换后的Map
	 */
	public static Map<String, Object> json2Map(String jsonString)
	{
	  JSONObject jsonObject;
	  try
	  {
		   jsonObject = new JSONObject(jsonString);
		   @SuppressWarnings("unchecked")
		   Iterator<String> keyIter = jsonObject.keys();
		   String key;
		   Object value;
		   Map<String, Object> valueMap = new HashMap<String, Object>();
		   while (keyIter.hasNext())
		   {
			    key = (String) keyIter.next();
			    value = jsonObject.get(key);
			    valueMap.put(key, value);
		   }
		   return valueMap;
	  }catch (JSONException e)
	  {
		  e.printStackTrace();
	  }
	  return null;
	}

	/**
	 * @brief 将 String 对象编码为 JSON格式，只需处理好特殊字符
	 * @param s String 对象
	 * @return JSON格式
	 */
	static String string2Json(final String s) {
		final StringBuilder sb = new StringBuilder(s.length() + 20);
		sb.append('\"');
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		sb.append('\"');
		return sb.toString();
	}

	/**
	 * @brief 将 Number 表示为 JSON格式
	 * @param number Number
	 * @return JSON格式
	 */
	/*private static String number2Json(final Number number) {
		return number.toString();
	}*/
	
	/**
	 * @brief  将 Boolean 表示为 JSON格式
	 * @param bool  Boolean
	 * @return JSON格式
	 */
	/*private static String boolean2Json(final Boolean bool) {
		return bool.toString();
	}*/
	
	/**
	 * @brief 将JSON格式字符串装换成Object对象
	 * @param jsonString 要转换的String
	 * @return 转换后的JOSN对象
	 */
	public static JSONObject json2Object(String jsonString)
	{
		JSONObject object = null;
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * @brief 把对象封装为JSON格式
	 * @param o 要转换的object
	 * @return 转换后的JSON
	 */
	/*private static String toJson(final Object o) {
		if (o == null) {
			return "null";
		}
		//根据业务需要我们的Map格式的数据value暂时只有String,int,boolean等基本类型
		if (o instanceof String)
		{
			return string2Json((String) o);
		}
		if (o instanceof Number) // Number
		{
			return number2Json((Number) o);
		}
		if (o instanceof Boolean) // Boolean
		{
			return boolean2Json((Boolean) o);
		}
		throw new RuntimeException("不支持的类型: " + o.getClass().getName());
	}*/
	
	/*
	
	/**
	 * @brief 把对象封装为JSON格式
	 * @param o 要转换的object
	 * @return 转换后的JSON
	 */
	@SuppressWarnings("unchecked")
	public static String toJson(final Object o) {
		if (o == null) {
			return "null";
		}
		if (o instanceof String) // String
		{
			return string2Json((String) o);
		}
		if (o instanceof Boolean) // Boolean
		{
			return boolean2Json((Boolean) o);
		}
		if (o instanceof Number) // Number
		{
			return number2Json((Number) o);
		}
		if (o instanceof HashMap) // Map
		{
			return map2Json((HashMap<String, Object>) o);
		}
		if (o instanceof Collection) // List Set
		{
			return collection2Json((Collection<Object>) o);
		}
		if (o instanceof Object[]) // 对象数组
		{
			return array2Json((Object[]) o);
		}
		if (o instanceof int[])// 基本类型数组
		{
			return intArray2Json((int[]) o);
		}
		if (o instanceof boolean[])// 基本类型数组
		{
			return booleanArray2Json((boolean[]) o);
		}
		if (o instanceof long[])// 基本类型数组
		{
			return longArray2Json((long[]) o);
		}
		if (o instanceof float[])// 基本类型数组
		{
			return floatArray2Json((float[]) o);
		}
		if (o instanceof double[])// 基本类型数组
		{
			return doubleArray2Json((double[]) o);
		}
		if (o instanceof short[])// 基本类型数组
		{
			return shortArray2Json((short[]) o);
		}
		if (o instanceof byte[])// 基本类型数组
		{
			return byteArray2Json((byte[]) o);
		}
		if (o instanceof Object) // 保底收尾对象
		{
			return object2Json(o);
		}
		throw new RuntimeException("不支持的类型: " + o.getClass().getName());
	}
	/**
	 * @brief 将JSON格式字符串装换成Object对象
	 * @param jsonString 要转换的String
	 * @return 转换后的JOSN对象
	 *//*
	public static JSONObject json2Object(String jsonString){
		JSONObject object = null;
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
		
	}
	
	/**
	 * @brief 将 Number 表示为 JSON格式
	 * @param number Number
	 * @return JSON格式
	 */
	static String number2Json(final Number number) {
		return number.toString();
	}

	/**
	 * @brief  将 Boolean 表示为 JSON格式
	 * @param bool  Boolean
	 * @return JSON格式
	 */
	static String boolean2Json(final Boolean bool) {
		return bool.toString();
	}

	/**
	 * @brief  将 Collection 编码为 JSON 格式 (List,Set)
	 * @param c Collection
	 * @return String格式
	 */
	static String collection2Json(final Collection<Object> c) {
		final Object[] arrObj = c.toArray();
		return toJson(arrObj);
	}

	/**
	 * @brief 将数组编码为 JSON 格式
	 * @param array 数组
	 * @return JSON 格式
	 */
	static String array2Json(final Object[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4); // 4次方
		sb.append('[');
		for (final Object o : array) {
			sb.append(toJson(o));
			sb.append(',');
		}
		// 将最后添加的 ',' 变为 ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief int数组转换为JSON
	 * @param array int数组
	 * @return JSON
	 */
	static String intArray2Json(final int[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final int o : array) {
			sb.append(Integer.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief long数组转换为JSON
	 * @param array long数组
	 * @return JSON
	 */
	static String longArray2Json(final long[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final long o : array) {
			sb.append(Long.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String booleanArray2Json(final boolean[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final boolean o : array) {
			sb.append(Boolean.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief float数组转换为JSON
	 * @param array float数组
	 * @return JSON
	 */
	static String floatArray2Json(final float[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final float o : array) {
			sb.append(Float.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief double数组转换为JSON
	 * @param array double数组
	 * @return JSON
	 */
	static String doubleArray2Json(final double[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final double o : array) {
			sb.append(Double.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief short数组转换为JSON
	 * @param array short数组
	 * @return JSON
	 */
	static String shortArray2Json(final short[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final short o : array) {
			sb.append(Short.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
    
	/**
	 * @brief byte数组转换为JSON
	 * @param array byte数组
	 * @return JSON
	 */
	static String byteArray2Json(final byte[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final byte o : array) {
			sb.append(Byte.toString(o));
			sb.append(',');
		}
		// set last ',' to ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
	/**
	 * @brief object对象转换为JSON
	 * @param bean object对象
	 * @return JSON
	 */
	public static String object2Json(final Object bean) {
		// 数据检查
		if (bean == null) {
			return "{}";
		}
		final Method[] methods = bean.getClass().getMethods(); // 方法数组
		final StringBuilder sb = new StringBuilder(methods.length << 4); // 4次方
		sb.append('{');
		for (final Method method : methods) {
			try {
				final String name = method.getName();
				String key = "";
				if (name.startsWith("get")) {
					key = name.substring(3);
					// 防死循环
					final String[] arrs = { "Class" };
					boolean bl = false;
					for (final String s : arrs) {
						if (s.equals(key)) {
							bl = true;
							continue;
						}
					}
					if (bl) {
						continue; // 防死循环
					}
				} else if (name.startsWith("is")) {
					key = name.substring(2);
				}
				if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
						&& method.getParameterTypes().length == 0) {
					if (key.length() == 1) {
						key = key.toLowerCase();
					} else if (!Character.isUpperCase(key.charAt(1))) {
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
					}
					final Object elementObj = method.invoke(bean);
					// System.out.println("###" + key + ":" +
					// elementObj.toString());
					sb.append('\"');
					sb.append(key); // 不能包含特殊字符
					sb.append('\"');
					sb.append(':');
					sb.append(toJson(elementObj)); // 循环引用的对象会引发无限递归
					sb.append(',');
				}
			} catch (final Exception e) {
				// e.getMessage();
				throw new RuntimeException("在将bean封装成JSON格式时异常："
						+ e.getMessage(), e);
			}
		}
		if (sb.length() == 1) {
			return bean.toString();
		} else {
			sb.setCharAt(sb.length() - 1, '}');
			return sb.toString();
		}
	}
	
	/**
	 * @brief boolean类型转String类型
	 * @param boolea 转换对象
	 * @return String 转换后的字符串
	 */
	public static String boolean2String(boolean boolea)
	{
		if(boolea)
		{
			return "1";
		}else{
			return "0";
		}
	}
	
	/**
	 * @brief int类型转boolean类型
	 * @param m  转换对象
	 * @return boolean  true表示1，false表示0
	 */
	public static boolean int2boolean(int m)
	{
		if(m==0)
		{
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @brief 判断字符串全部为数字
	 * @param str 字符串
	 * @return boolean true表示全为数字，false表示否
	 */
	public static boolean isNumeric(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
