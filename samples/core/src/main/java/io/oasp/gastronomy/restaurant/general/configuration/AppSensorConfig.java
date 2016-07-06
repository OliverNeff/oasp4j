package io.oasp.gastronomy.restaurant.general.configuration;

import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.event.EventManager;
import org.owasp.appsensor.integration.springsecurity.context.AppSensorSecurityContextRepository;
import org.owasp.appsensor.local.event.LocalEventManager;
import org.owasp.appsensor.local.response.LocalResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.web.context.SecurityContextRepository;

import io.oasp.gastronomy.restaurant.general.service.impl.rest.AppSensorSecurityEventListener;

/**
 * TODO olneff This type ...
 *
 * @author olneff
 * @since dev
 */
@Configuration
@ComponentScan(basePackages = "org.owasp.appsensor", excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
AppSensorClient.class, EventManager.class }))
public class AppSensorConfig {

  @Bean
  public SecurityContextRepository securityContextRepository() {

    return new AppSensorSecurityContextRepository();
  }

  @Bean
  public AppSensorSecurityEventListener springSecurityEventListener() {

    return new AppSensorSecurityEventListener();
  }

  @Bean
  @DependsOn({ "eventManager", "responseHandler" })
  public AppSensorClient appSensorClient() {

    return new AppSensorClient();
  }

  @Bean
  public LocalEventManager eventManager() {

    return new LocalEventManager();
  }

  @Bean
  public LocalResponseHandler responseHandler() {

    return new LocalResponseHandler();
  }
}