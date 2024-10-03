package com.softy.softy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.softy.softy.Modele.Modele;

@SpringBootApplication
@EnableRedisHttpSession
public class SoftyApplication {

	public static Modele monModel;

	public static void main(String[] args) {

		SoftyApplication.monModel = new Modele();
		SpringApplication.run(SoftyApplication.class, args);
	
	}

}
