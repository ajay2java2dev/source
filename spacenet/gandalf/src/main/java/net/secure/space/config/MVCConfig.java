package net.secure.space.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

/**
 * Created by AjayMenon on 12/25/2016.
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/jsp/**").addResourceLocations("/jsp/");
		//registry.addResourceHandler("/template/**").addResourceLocations("/template/");
		logger.info("Gandalf added resources");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		logger.info("Gandalf enabled configurer");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//administration
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("locale");
		localeResolver.setCookieMaxAge(30);
		localeResolver.setDefaultLocale(new Locale("en-US", "US"));
		return localeResolver;
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
