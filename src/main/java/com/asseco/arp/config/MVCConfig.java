package com.asseco.arp.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {

	 @Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	        for(HttpMessageConverter<?> converter: converters) {
	            if(converter instanceof MappingJackson2HttpMessageConverter) {
	                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter)converter).getObjectMapper();
	                mapper.setSerializationInclusion(Include.NON_NULL);
	                mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,true);
	                mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	                mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	                mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
	                mapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
	                mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
	                
	            }
	        }
	    }

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
		}

}
