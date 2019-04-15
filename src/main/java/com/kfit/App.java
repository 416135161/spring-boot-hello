package com.kfit;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * 在这里我们使用@SpringBootApplication指定这是一个 spring boot的应用程序.
 * @author Angel -- 守护天使
 * @version v.0.1
 * @date 2016年12月10日
 */
//extends WebMvcConfigurerAdapter
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Configuration
@EnableScheduling
public class App extends SpringBootServletInitializer implements WebApplicationInitializer {
	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		
//		/*
//		 * 1、需要先定义一个 convert 转换消息的对象;
//		 * 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//		 * 3、在convert中添加配置信息.
//		 * 4、将convert添加到converters当中.
//		 * 
//		 */
//		
//		// 1、需要先定义一个 convert 转换消息的对象;
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		
//		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat
//        );
//		
//		//3、在convert中添加配置信息.
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        
//        //4、将convert添加到converters当中.
//    	converters.add(fastConverter);
//		
//	}
	
	
	/**
	 * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		
		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		
		//3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		
		
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	/**
	 * 这是springloader的配置方式：-javaagent:.\lib\springloaded-1.2.4.RELEASE.jar -noverify
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 在main方法进行启动我们的应用程序.
		 */
		SpringApplication.run(App.class, args);
	}
}
