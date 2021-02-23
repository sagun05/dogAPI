package com.sagun.solutions.service.rs;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sagun.solutions.service.DogBreedService;
import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;
import com.sagun.solutions.errors.ErrorCodes;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
public class DogBreedController implements IDogBreedController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DogBreedService dogBreedService;
		

	@Autowired
	public DogBreedController(DogBreedService dogBreedService) {
		this.dogBreedService = dogBreedService;		
	}
	
	@Override
	public Response getAllDogBreeds() {
		try {
			List<DogBreed> lStd = dogBreedService.getAllDogBreeds();
			return Response.ok(lStd).build();
		}catch(Exception e) {
			log.error("Internal Application Error in getAllDogBreeds API: ", e.getMessage());
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ErrorCodes.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
		}		
	}

	@Override
	public Response getDogBreedByName(String breed) {
		try {
			if(null == breed || breed.equals("")) {
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(ErrorCodes.MISSING_PARAMETERS_ERROR).type(MediaType.APPLICATION_JSON).build();
			}		
			return Response.ok(dogBreedService.getDogBreedByName(breed)).build();
		}catch(Exception e) {
			log.error("Internal Application Error in getDogBreedByName API:", e.getMessage());
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ErrorCodes.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
		}		
	}
	
	@Override
	public Response voteDogImage(DogImage dogImage) {
		try {
			if(null == dogImage) {
				log.error("MISSING_PARAMETERS_ERROR in voteDogImage API:");
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(ErrorCodes.MISSING_PARAMETERS_ERROR).type(MediaType.APPLICATION_JSON).build();
			}
			//we need to add here  validation
			
			dogBreedService.updateDogBreed(dogImage);
		}catch(Exception e) {
			log.error("Internal Application Error in voteDogBreed API:", e.getMessage());
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ErrorCodes.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok().build();
	}
	
	@Override
	public Response getDogImageByIdentity(String imageIdentity) {
		try {
			if(null == imageIdentity || imageIdentity.equals("")) {
				log.error("MISSING_PARAMETERS_ERROR in getDogImageByIdentity API:");
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(ErrorCodes.MISSING_PARAMETERS_ERROR).type(MediaType.APPLICATION_JSON).build();
			}		
			return Response.ok(dogBreedService.getDogImageByIdentity(imageIdentity)).build();
		}catch(Exception e) {
			log.error("Internal Application Error in getDogImageByIdentity API:", e.getMessage());
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ErrorCodes.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
}
