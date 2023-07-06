package com.ridsys.rib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ridsys.rib.service.OnlinePaymentService;
import com.ridsys.rib.service.impl.OnlinePaymentServiceImpl;




@SpringBootApplication
@EnableScheduling
//@EnableEurekaClient
public class RInternetBillingApplication extends SpringBootServletInitializer {

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RInternetBillingApplication.class);
	}

	public static void main(String[] args) {
	
		SpringApplication.run(RInternetBillingApplication.class, args);
	
	}

}
