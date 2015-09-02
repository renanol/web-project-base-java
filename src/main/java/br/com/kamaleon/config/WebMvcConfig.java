package br.com.kamaleon.config;

import java.io.IOException;

import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	
	@Autowired
	private ApplicationContext context;


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
	public VelocityConfigurer velocityConfig() throws VelocityException, IOException {
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		
		velocityConfigurer.setResourceLoaderPath("/WEB-INF/views/");		
		
		velocityConfigurer.setConfigLocation(context.getResource("/WEB-INF/conf/velocity.properties"));
		
		/*Map<String, Object> velocityPropertiesMap = new HashMap<>();
		
		velocityPropertiesMap.put("velocimacro.library", "VM_global_library.vm");
		
		velocityConfigurer.setVelocityPropertiesMap(velocityPropertiesMap);*/
		
		return velocityConfigurer;
		
	}
	
	@Bean
	public ViewResolver viewResolver() {

		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setSuffix(".vm");		
		viewResolver.setPrefix("");
		viewResolver.setCache(true);
		viewResolver.setToolboxConfigLocation("/WEB-INF/conf/toolbox.xml");
		return viewResolver;
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	registry.addResourceHandler("/assets/js/**").addResourceLocations("/resources/assets/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/css/**").addResourceLocations("/resources/assets/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/img/**").addResourceLocations("/resources/assets/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/fonts/**").addResourceLocations("/resources/assets/fonts/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/sound/**").addResourceLocations("/resources/assets/sound/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/swf/**").addResourceLocations("/resources/assets/swf/").setCachePeriod(31556926);

    }


}
