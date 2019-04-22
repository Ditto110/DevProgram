package com.ditto;

import com.ditto.dynamicdatasource.DynamicDataSourceRegister;
import com.mipt.fm.util.SpringApplicationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class, SpringApplicationUtil.class})
@EnableTransactionManagement
public class MainApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder).sources(MainApplication.class);
	}
}
