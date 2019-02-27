package com.lss.scdool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
//public class YbschholAppServerApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(YbschholAppServerApplication.class, args);
//	}
//}

public class YbschholAppServerApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(YbschholAppServerApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(YbschholAppServerApplication.class, args);
	}
}
