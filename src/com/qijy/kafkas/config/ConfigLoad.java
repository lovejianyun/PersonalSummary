package com.qijy.kafkas.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ConfigLoad {

	
	private static final Logger log = LoggerFactory.getLogger(ConfigLoad.class); // 日志对象
	protected Properties properties;

	// 配置文件名
	public static final String CONFIGFILTENAME = "application.properties";
	public static final String ENCODING = "UTF-8";
	
	MonConfig monConfig2 = MonConfig.INSTANCE;
	public void initConfig(){
		Field[] fields = MonConfig.class.getDeclaredFields();
		//加载配置文件
		initConfigFile();
		for(Field field:fields){
			if(field.getAnnotations().length>0){
				Config conf = field.getAnnotation(Config.class);
				if(conf != null){
					String name = conf.name();
					String dataType = conf.type();
					String split = conf.split();
					field.setAccessible(true);
					try {
						Object def = field.get(monConfig2);
						String defStr = "";
						if(def != null){ 
							defStr = String.valueOf(def);
						}
						loadConfigFromFile(field,name, defStr,dataType,split);
					} catch (Exception e) {
//						log.error("配置项[sec="+sec+" name="+name+"]数据加载异常", e);
//						log.info("密码是明文");
					} 
					field.setAccessible(false);
				}
			}
		}
	}

	
	protected void initConfigFile(){
			try {
				properties = new Properties();
				properties.load(new FileReader(CONFIGFILTENAME));
			}catch(Exception e){
				log.error("读取配置文件出现错误",e);
			}

	}
	
	protected void loadConfigFromFile(Field field,String name,String def,String dataType,String split) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String tmpStr = def;
		if(!properties.containsKey(name)){
			update(name,def);
		}else{
			tmpStr = properties.getProperty(name);
			if(StringUtils.isNullOrEmpty(tmpStr)){
				update(name,def);
			}else{
				setConfigValue(field, name, tmpStr,def,dataType,split);
			}
		}
	}
	
	private void setConfigValue(Field field,String name,String val,String def,String dataType,String split) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String fieldName = field.getName();
		String methodName ="set"+StringUtils.toUpperCaseFirstOne(fieldName);
		Method setMethod = MonConfig.class.getMethod(methodName, field.getType());
		String typeName = field.getType().getSimpleName();
		Object value = val;
		switch(typeName){
			case "int":
			case "Integer":
				value = StringUtils.checkInteger(val);
				break;
			case "long":
			case "Long":
				value = StringUtils.checkLong(val, 0);
				break;
			case "boolean":
			case "Boolean":
				value = Boolean.valueOf(val);
				break;
			case "List":
				value = toList(val, split);
				break;
			case "Set":
				value = toSet(val, split);
				break;
			default: value = val;
				break;
		}
		if(dataType.equals(Config.PASSWORD)){
			String deValue = AESEncoder.AESDncode(SECRETKEY, value.toString().trim());
			if (deValue == null){
				update(name,AESEncoder.AESEncode(SECRETKEY, value.toString().trim()));
			}else{
				value = deValue;
			}
		}
		setMethod.invoke(monConfig2, value);
	}

	
	private List<String> toList(String confStr,String split){
		if(StringUtils.isNullOrEmpty(confStr)){
			return new ArrayList<String>();
		}
		String[] arr = confStr.split(split);
		return Arrays.asList(arr);
	}
	
	private Set<String> toSet(String confStr,String split){
		return new HashSet<>(toList(confStr, split));
	}

	
	
	public static final String SECRETKEY = "mon_analysis";


	public void update(String key, String value) {
		properties.setProperty(key, value);
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(CONFIGFILTENAME);
			//将Properties中的属性列表（键和元素对）写入输出流
			properties.store(oFile, "");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
