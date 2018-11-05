/** 
 * <pre>项目名称:lastmaven 
 * 文件名称:JsonUtil.java 
 * 包名:com.jk.cjl.utils 
 * 创建日期:2018年7月13日下午4:09:23 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.utils;


import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/** 
 * <pre>项目名称：HttpClient-Demo    
 * 类名称：JsonUtil    
 * 类描述：    
 * 创建人：王鹏奇  
 * 创建时间：2018年9月13日 下午3:04:58    
 * 修改人：王鹏奇      
 * 修改时间：2018年9月13日 下午3:04:58    
 * 修改备注：       
 * @version </pre>    
 */
public class JsonUtil {
	
	/**
	 * 将字符串转成实体类，允许斜杠等字符串
	 */
	public static <T> T jsonToEntity(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 允许反斜杆等字符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        return mapper.readValue(json, clazz);
    }
	
	/**
	 * 实体类转JSON字符串
	 */
	public static String entityToJson(Object entity){
		return JSON.toJSONString(entity);
	}
	
	/**
	 * 将字符串转成JsonNode，允许斜杠等字符串
	 */
	public static JsonNode jsonToJsonNode(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 允许反斜杆等字符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        //允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        return mapper.readValue(json, JsonNode.class);
    }
	
	public static <T> String objectToJson(Object object, Class<T> cls)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(cls);
		String reqJson = mapper.writeValueAsString(object);
		return reqJson;
	}
	
}

