package br.com.kamaleon.config;

import java.util.HashSet;
import java.util.Set;

import nz.net.ultraq.thymeleaf.LayoutDialect;

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
import org.springframework.web.servlet.view.XmlViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

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
	public Set<IDialect> thymeleafDialects() {
		Set<IDialect> dialects = new HashSet<IDialect>();
		dialects.add(new SpringStandardDialect());
		dialects.add(new LayoutDialect());
		return dialects;
	}

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		//NB, selecting HTML5 as the template mode.
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		return resolver;

	}
	
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.setDialects(thymeleafDialects());
		return engine;
	}

	@Bean
	public ViewResolver viewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[]{"*"});
		viewResolver.setCache(false);
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
