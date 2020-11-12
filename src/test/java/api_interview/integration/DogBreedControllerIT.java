package api_interview.integration;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.sagun.solutions.Application;
import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;
import com.sagun.solutions.service.rs.DogBreedController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class}, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DogBreedControllerIT {
	
	@Autowired
	DogBreedController dogBreedController;
	
	@SuppressWarnings("unchecked")	
	@Test
	public void getAllDogBreedsTest() {
		Response response = dogBreedController.getAllDogBreeds();
		assertNotNull(response);
		if(response.getStatus()!=200){
			fail("Unexpected Error");
		}else{			
			List<DogBreed>  lDB = (List<DogBreed>) response.getEntity();			
			assertNotNull(lDB);
			assertEquals("Number of resouces is not equal to requested size of 4.", 4, lDB.size());
		}
	}
	
	@Test
	public void getDogBreedByBreedTest() {
		Response response = dogBreedController.getDogBreedByName("BullDog");
		assertNotNull(response);
		if(response.getStatus()!=200){
			fail("Unexpected Error");
		}else{			
			DogBreed  breed = (DogBreed) response.getEntity();			
			assertNotNull(breed);			
			assertEquals("Requested Breed is not matching for BullDog.", "BullDog", breed.getBreedName());
		}
	}
	
	@Test
	public void updateVoteForDogImageTest() {
		DogImage dogImage = new DogImage();
		dogImage.setImage_id(88);
		dogImage.setImageIdentity("glT1i6n");
		dogImage.setImageUrl("http://i.imgur.com/r2uqgVQ.jpg");
		dogImage.setVote(1);
		
		Response response = dogBreedController.voteDogImage(dogImage);
		assertNotNull(response);
		if(response.getStatus()!=200){
			fail("Unexpected Error");
		}
	}
	
	@Test
	public void missingBreedRequestTest() {
		Response response = dogBreedController.getDogBreedByName("");
		assertNotNull(response);
		assertEquals("Missing Input. BreedName", 400, response.getStatus());
		
	}
	
}
