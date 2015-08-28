package br.com.kamaleon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;


/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.kamaleon.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");
    }

    @Bean
    public MultipartResolver multipartResolver()
    {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 * 512);
        return resolver;
    }
    
	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public VelocityConfigurer velocityConfig() {
	    VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
	    
	    return velocityConfigurer;
	}
	
	@Bean
	public ViewResolver viewResolver() {

		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setSuffix(".vm");		
		viewResolver.setPrefix("");
		viewResolver.setCache(true);
		return viewResolver;
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	registry.addResourceHandler("/highcharts/**").addResourceLocations("/resources/highcharts/").setCachePeriod(31556926);
    	registry.addResourceHandler("/highcharts/adapters/**").addResourceLocations("/resources/highcharts/adapters/").setCachePeriod(31556926);
    	registry.addResourceHandler("/highcharts/modules/**").addResourceLocations("/resources/highcharts/modules/").setCachePeriod(31556926);
    	registry.addResourceHandler("/highcharts/themes/**").addResourceLocations("/resources/highcharts/themes/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/js/**").addResourceLocations("/resources/assets/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/css/**").addResourceLocations("/resources/assets/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/img/**").addResourceLocations("/resources/assets/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/font/**").addResourceLocations("/resources/assets/font/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/images/**").addResourceLocations("/resources/assets/images/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/avatars/**").addResourceLocations("/resources/assets/avatars/").setCachePeriod(31556926);

    }


}
