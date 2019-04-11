package hello;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController      // @RestController  相当于 @Controller 加上  @ResponseBody
@SpringBootApplication
//@EnableAutoConfiguration
public class HelloController extends SpringBootServletInitializer{

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return  application.sources(HelloController.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	} 
	
	@RequestMapping(value="/example/hello",method = RequestMethod.GET)
	public String hello(){
		return "config is success !";
	}
	
}
