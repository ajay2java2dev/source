package net.secure.space.config;

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
@EnableWebMvc
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//administration
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");

		//dashboard
		registry.addViewController("/dash").setViewName("dashboard");
		registry.addViewController("/db").setViewName("dashboard");

		//login page.
		registry.addViewController("/hello").setViewName("hello");

		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(jspViewResolver());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
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
		viewResolver.setOrder(1);
		return viewResolver;
	}

}
