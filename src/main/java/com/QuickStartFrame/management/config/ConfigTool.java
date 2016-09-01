package com.QuickStartFrame.management.config;

import org.springframework.beans.factory.InitializingBean;

/**
 * 配置信息的工具类，在spring-mybatis文件的bean中配置
 */
public class ConfigTool implements InitializingBean{
	


	@Override
	public void afterPropertiesSet() throws Exception {
		System.err.println("Bean初始化完成");
	}
}