package com.pds.carking.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper () {
		ModelMapper modelMapper = new  ModelMapper();
		return modelMapper;
	}
	
	@Bean
	public Faker faker () {
		Faker faker = new Faker(new Locale("pt-BR"));
		return faker;
	}
}
