package com.sagun.solutions;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.sagun.solutions.service.rs.DogBreedController;

@Component
@ApplicationPath("/test-app")
public class JerseyConfig extends ResourceConfig {
	   public JerseyConfig() {
		   register(DogBreedController.class);		   
	}
} 