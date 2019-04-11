package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController      // @RestController = @Controller + @ResponseBody
//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class HelloController {
 
	
	public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}
	
	@Value("${foo}")
	String foo;
	
	@RequestMapping(value="/example/hello",method = RequestMethod.POST)
	public String hello(){
		return "config is :"+foo;
	}
	
	@RequestMapping(value="/example/hello_get",method = RequestMethod.GET)
	public String hello1(String name){
		return "config is :"+foo+","+name;
	}
	
	
	
}
