/**
 * 
 */
package com.vzwcorp.pricinglab.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author kovelde
 * 
 * This class should be used to get Spring Context in non spring beans.
 *
 */
@Component
public class SpringContext implements ApplicationContextAware {
	  private static ApplicationContext context;

	  @SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
	    this.context = context;
	  }
	  public static ApplicationContext getApplicationContext() {
	    return context;
	  }
	}
