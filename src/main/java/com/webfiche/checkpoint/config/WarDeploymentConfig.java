package com.webfiche.checkpoint.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.DynamicRegistrationBean;
import org.springframework.context.annotation.Configuration;

/**
 * In external Tomcat deployment, Spring Boot 3.4+ sets failOnError=true on
 * DynamicRegistrationBean, but ServletContextInitializerBeans can emit a
 * duplicate registration for springSecurityFilterChain (DelegatingFilterProxy
 * + auto-wrapped FilterChainProxy).  The second addFilter() call returns null
 * and would throw.  Setting failOnError=false lets the duplicate log a warning
 * instead; the first (successful) registration remains in place.
 */
@Configuration
public class WarDeploymentConfig implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DynamicRegistrationBean<?> reg) {
            reg.setIgnoreRegistrationFailure(true);
        }
        return bean;
    }
}
