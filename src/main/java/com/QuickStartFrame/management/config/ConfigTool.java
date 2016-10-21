package com.QuickStartFrame.management.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 配置信息的工具类，在spring-mybatis文件的bean中配置
 */
public class ConfigTool implements InitializingBean{
	public static final Logger logger  = Logger.getLogger(ConfigTool.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//此处可以添加服务器启动时执行的任务
		logger.info("bean初始化完成");
		
	}
}