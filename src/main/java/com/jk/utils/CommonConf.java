/** 
 * <pre>项目名称:maven-Power 
 * 文件名称:CommonConf.java 
 * 包名:com.jk.wpq 
 * 创建日期:2018年10月17日上午11:46:10 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.jk.utils;

/** 
 * <pre>项目名称：maven-Power    
 * 类名称：CommonConf    
 * 类描述： 公共常用类   
 * 创建人：王鹏奇  
 * 创建时间：2018年10月17日 上午11:46:10    
 * 修改人：王鹏奇      
 * 修改时间：2018年10月17日 上午11:46:10    
 * 修改备注：       
 * @version </pre>    
 */
public class CommonConf {

	/**
	  * 设置缓存导航树Key
	 */
	public static final String NAV_CACHE_KEY = "navCache";
	
	/**
	   * 设置缓存权限Key
	 */
	public static final String POWER_CACHE_KEY = "powerCache";
	
	/**
	  * 设置缓存导航树过期时间 单位:分钟
	 */
	public static final Long NAV_CACHE_TIME_OUT = 30L;
	
	/**
	 * 设置设置缓存权限过期时间 单位:分钟
	 */
	public static final Long POWER_CACHE_TIME_OUT = 30L;
	
	/**
	  * 短信平台 ACCOUNTSID
	 */
	public static final String ACCOUNTSID = "61b7e7192dba4f498fb99438bc5fb623";
	
	/**
	  * 短信平台 AUTH_TOKEN
	 */
	public static final String AUTH_TOKEN = "43501da7358f4ea5ad1023692b239e0e";
	
	/**
	   * 发送短信平台地址
	 */
	public static final String SMS_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	
	/**
	   * 短信模板ID
	 */
	public static final String TEMPLATEID = "769116221";
	
	/**
	   * 缓存验证码Key
	 */
	public static final String RANDOM_CACHE = "yzm";
	
	/**
	   * 获取短信验证码锁 Key
	 */
	public static final String RANDOM_LOCK = "lock";
	
	/**
	   * 发送验证码返回成功状态
	 */
	public static final String RESP_CODE = "00000";

	/**
	   * 验证码缓存有效时间 单位 分钟
	 */
	public static final Long RANDOM_CACHE_TIME_OUT = 30L;
	
	/**
	   *获取验证码时间锁 单位 秒
	 */
	public static final Long RANDOM_LOCK_TIME = 60L;
	
	/**
	   *物流接口
	 */
	public static final String EXPRESS_URL = "http://www.kuaidi100.com/query";
	
	/**
	 *物流接口成功调用状态码
	 */
	public static final String EXPRESS_STATUS = "200";
	
	/**
	 * webservice 认证密码
	 */
	public static final String WEBSERVICE_PASS = "admin";
}
