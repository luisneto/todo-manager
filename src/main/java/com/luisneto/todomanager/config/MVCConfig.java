package com.luisneto.todomanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Luis Neto
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.luisneto.todomanager.rest.controller"})
public class MVCConfig extends WebMvcConfigurerAdapter {

}
