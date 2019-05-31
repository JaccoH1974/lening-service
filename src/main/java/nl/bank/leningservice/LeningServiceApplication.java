package nl.bank.leningservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class LeningServiceApplication extends SpringBootServletInitializer implements WebApplicationInitializer, WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(LeningServiceApplication.class, args);
	}

}
