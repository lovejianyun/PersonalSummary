package com.qijy.kafkas.config;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * 
 * @author JoNllen
 * @since V 1.0
 */
public final class StringUtils {
	private static final String DEFAULT_FOLDER_SEPARATOR = "/";
	private static final char SUFFIX_SEPARATOR = '.';

	/**
	 * 检查CharSequence是否为NULL或为空
	 * 
	 * <pre>
	 * StringUtils.isNullOrEmpty(null) = true
	 * StringUtils.isNullOrEmpty("") = true
	 * StringUtils.isNullOrEmpty(" ") =false
	 * StringUtils.isNullOrEmpty("Hello")= false
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(CharSequence s) {
		return (s == null || s.length() <= 0);
	}

	/**
	 * 检查字符串是否为NULL或为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return isNullOrEmpty((CharSequence) s);
	}

	/**
	 * 检查字符串中是否包含空字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean containsEmpty(CharSequence s) {
		if (isNullOrEmpty(s))
			return true;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (Character.isWhitespace(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 去掉开始和结尾处的空白
	 * 
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		if (isNullOrEmpty(s))
			return s;

		StringBuffer buf = new StringBuffer(s);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 去除字符串中所有的空白
	 * 
	 * @param s
	 * @return
	 */
	public static String trimAll(String s) {
		if (isNullOrEmpty(s)) {
			return s;
		}
		StringBuffer buf = new StringBuffer(s);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index)))
				buf.deleteCharAt(index);
			else
				index++;
		}
		return buf.toString();
	}

	/**
	 * 去除字符串左边的字符
	 * 
	 * @param s
	 * @return
	 */
	public static String lTrim(String s) {
		if (isNullOrEmpty(s))
			return s;
		StringBuffer buf = new StringBuffer(s);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 去除字符串中右边空白
	 * 
	 * @param s
	 * @return
	 */
	public static String rTrim(String s) {
		if (isNullOrEmpty(s))
			return s;
		StringBuffer buf = new StringBuffer(s);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 去掉字符串中左边等同的字符
	 * 
	 * @param s
	 * @param existenceChar
	 * @return
	 */
	public static String lTrim(String s, char existenceChar) {
		if (isNullOrEmpty(s))
			return s;
		StringBuffer buf = new StringBuffer(s);
		while (buf.length() > 0 && buf.charAt(0) == existenceChar)
			buf.deleteCharAt(0);

		return buf.toString();
	}

	/**
	 * 去掉字符串中右等同的字符
	 * 
	 * @param s
	 * @param existenceChar
	 * @return
	 */
	public static String rTrim(String s, char existenceChar) {
		if (isNullOrEmpty(s))
			return s;
		StringBuffer buf = new StringBuffer(s);
		while (buf.length() > 0 && buf.charAt(buf.length() - 1) == existenceChar)
			buf.deleteCharAt(buf.length() - 1);

		return buf.toString();
	}

	/**
	 * 统计sub在s中出现的次数
	 * 
	 * @param s
	 * @param sub
	 * @return
	 */
	public static int countExistenceOf(String s, String sub) {
		if (s == null || sub == null || s.length() == 0 || sub.length() == 0)
			return 0;
		int count = 0, pos = 0, index = 0;
		while ((index = s.indexOf(s, pos)) != -1) {
			count++;
			pos = index + sub.length();
		}
		return count;
	}

	/**
	 * 用单引号将字符串包括起来 如:'HELLO'
	 * 
	 * @param s
	 * @return
	 */
	public static String quote(String s) {
		return (s != null ? "'" + s + "'" : null);
	}

	/**
	 * 将如果是String对象用单引号包括，如果不是String对象将返回原型
	 * 
	 * @param o
	 * @return
	 */
	public static Object quote(Object o) {
		return (o instanceof String ? quote((String) o) : o);
	}

	/**
	 * 截取最后一个'.'开始的后面字符串
	 * 
	 * @param qualifiedName
	 * @return
	 */
	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	/**
	 * 截取指定字符最后出现位置开始后面字符串
	 * 
	 * @param qualifiedName
	 * @param separator
	 * @return
	 */
	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	/**
	 * 获取文件名的后缀 如: "d:\xx\tfile.txt" -> "txt"
	 * 
	 * @param fullFileName
	 * @return
	 */
	public static String getFileNameSuffix(String fullFileName) {
		if (fullFileName == null)
			return null;
		int index = fullFileName.lastIndexOf(SUFFIX_SEPARATOR);
		return (index != -1 ? fullFileName.substring(index + 1) : null);
	}

	/**
	 * 截取文件名称 如:"http://www.xxx.com/path/mytxt.txt" ->"mytxt.txt"
	 * 
	 * @param fullFileName
	 * @return
	 */
	public static String getFileName(String fullFileName) {
		if (fullFileName == null)
			return null;
		int index = fullFileName.lastIndexOf(DEFAULT_FOLDER_SEPARATOR);
		return (index != -1 ? fullFileName.substring(index + 1) : null);
	}
	

	public static String arrayToString(List<String> cache){
		if(null == cache || cache.isEmpty())
			return null;
		
		StringBuffer sb = new StringBuffer();
		for (String st : cache) {
			sb.append(";").append(st);
		}
		sb =sb.deleteCharAt(0);
		return sb.toString();
	}
	
	public static String joinKey(String sign,String ...calums){
		StringBuffer key = new StringBuffer(); 
		if(calums.length==1){
			return calums[0];
		}
		if(calums.length==0){
			return "";
		}
		for(String calum:calums){
			key.append(calum).append(sign);
		}
		if(sign.length()>1){
			key.delete(key.lastIndexOf(sign),key.length());
		}
		if(sign.length()==1){
			key.deleteCharAt(key.length()-1);
		}
		return key.toString();
	}
	
    
    /**
     * 校验整型数据是否为空，若为空默认设置为0。
     * @param numberStr
     * @return
     */
    public static int checkInteger(String numberStr){
    	int number = StringUtils.isNullOrEmpty(numberStr)?0:Integer.valueOf(numberStr);
    	return number;
    }
    public static int checkInteger(String numberStr,int defaultNum){
    	try{
	    	int number = StringUtils.isNullOrEmpty(numberStr)?defaultNum:Integer.valueOf(numberStr);
	    	return number;
    	}catch(NumberFormatException e){
    		return defaultNum;
    	}
    }
    public static long checkLong(String numberStr){
    	long number = StringUtils.isNullOrEmpty(numberStr)?0l:Long.valueOf(numberStr);
    	return number;
    }
    public static long checkLong(String numberStr,long defaultValue){
    	try{
	    	long number = StringUtils.isNullOrEmpty(numberStr)?defaultValue:Long.valueOf(numberStr);
	    	return number;
    	}catch(Exception e){
    		return defaultValue;
    	}
    }
    public static double checkDouble(String numberStr){
    	try{
	    	double number = StringUtils.isNullOrEmpty(numberStr)?0d:Double.valueOf(numberStr);
	    	return number;
    	}catch(Exception e){
    		return 0d;
    	}
    }
    public static String checkString(String str){
    	String str_ = isNullOrEmpty(str)?"":str;
    	return str_;
    }
    
    /**
     * 校验key和value是否有空，若其中有空，则不能插入map
     * @param key
     * @param value
     * @param map
     */
    public static void checkEmptyToMap(String key,String value,Map<String,String> map){
    	if(isNullOrEmpty(key) || isNullOrEmpty(value)){
    		return;
    	}
    	map.put(key, value);
    }
    
    /**
     * 方法名称:formatEvtTm
     * 方法描述: 格式化事件时间，如果带有毫秒则去掉毫秒 
     * @return String
     * 创建人： hl
     * 创建时间：2016-9-28 下午1:37:54
     */
    public static String formatEvtTm(String evtTm){
    	if(evtTm.contains(".")){
    		return evtTm.substring(0, evtTm.indexOf("."));
    	}
    	
    	return evtTm;
    }
    
    /**
     * 方法名称:isNumeric
     * 方法描述:用正则表达式判断字符串是否为数字（含负数）   
     * @param str
     * @return boolean
     * 创建人： hl
     * 创建时间：2016-10-8 下午4:04:34
     */
    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }else {
            return false;
        }
    }
    
	public static String toUpperCaseFirstOne(String s) {
		if(isNullOrEmpty(s)){
			return s;
		}
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else {
			char[] temp = s.toCharArray();
			temp[0] = Character.toUpperCase(temp[0]);
			return String.valueOf(temp);
		}
	}

	/**
	 * 判断当前字符串是不是给定的日期格式
	 * @param dataStr yyyy-MM-dd HH:mm:ss[.SSS]
	 * @return
	 */
	public static boolean isValidate(String dataStr){
		if(isNullOrEmpty(dataStr)){
			return false;
		}
		//定义正则表达式
		String regex = "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}(.{1}\\d{3})?$";
		//编译正则表达式
		Pattern pattern = Pattern.compile(regex);
		//日期格式是否与正则表达式匹配
		Matcher matcher = pattern.matcher(dataStr);
		return matcher.matches();
	}
}	
