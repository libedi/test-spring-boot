package com.libedi.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Bean
	public FreeMarkerConfigurationFactoryBean freemarkerConfiguration(){
		FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
		freemarkerConfiguration.setTemplateLoaderPath("classpath:templates");
		freemarkerConfiguration.setDefaultEncoding("UTF-8");
		return freemarkerConfiguration;
	}
}
