package com.pds.carking.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.javafaker.Faker;
import com.pds.carking.util.faker.FakerExtras;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper () {
		ModelMapper modelMapper = new  ModelMapper();
		return modelMapper;
	}
	
	@Bean
	public FakerExtras faker () {
		return new FakerExtras(new Locale("pt-BR"));
	}
}
