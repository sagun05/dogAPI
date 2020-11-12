package api_interview.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;
import com.sagun.solutions.service.DogBreedService;
import com.sagun.solutions.service.rs.DogBreedController;

import api_interview.MockedDogBreedTestData;
import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class DogBreedControllerTest {

	@Mock
	private DogBreedService dogBreedService = Mockito.mock(DogBreedService.class);

	@InjectMocks
	DogBreedController dogBreedController = new DogBreedController(dogBreedService);

	@Test
	@SuppressWarnings("unchecked")
	public void getAllDogBreedsAndVerifyTheDataExists() {
		List<DogBreed> lDBreeds = MockedDogBreedTestData.getMultipleDogBreeds();
		Mockito.when(dogBreedService.getAllDogBreeds()).thenReturn(lDBreeds);
		
		Response response = dogBreedController.getAllDogBreeds();
		
		assertNotNull(response);
		List<DogBreed> lB = (List<DogBreed>) response.getEntity();
		assertNotNull(lB);
		assertEquals(2, lB.size());
		assertEquals("abc", lB.get(0).getBreedName());
		assertNotNull(lB.get(0).getImgList());
		assertEquals(1, lB.get(0).getImgList().size());
		assertEquals("http1", lB.get(0).getImgList().get(0).getImageUrl());
		assertEquals(1, lB.get(0).getImgList().get(0).getVote());
		assertEquals("identity1", lB.get(0).getImgList().get(0).getImageIdentity());
	}
	
	
	@Test	
	public void getDogBreedByNameAndVerifyTheDataExists() {
		DogBreed dogBreed = MockedDogBreedTestData.getSingleDogBreed();			
		Mockito.when(dogBreedService.getDogBreedByName(dogBreed.getBreedName())).thenReturn(dogBreed);
		
		Response response = dogBreedController.getDogBreedByName("abc");
		
		
		assertNotNull(response);
		DogBreed breed = (DogBreed) response.getEntity();
		assertNotNull(breed);
		
		assertEquals("abc", breed.getBreedName());
		assertNotNull(breed.getImgList());
		assertEquals(1, breed.getImgList().size());
		assertEquals("http1", breed.getImgList().get(0).getImageUrl());
		assertEquals(1, breed.getImgList().get(0).getVote());
		assertEquals("identity1", breed.getImgList().get(0).getImageIdentity());
	}
	
	@Test
	public void missingBreedNameInputSoReturnBadRequestErrorCode() {
		DogBreed dogBreed = MockedDogBreedTestData.getSingleDogBreed();
		
		List<DogBreed> lDBreeds = new ArrayList<>();
		lDBreeds.add(dogBreed);
		Response response = dogBreedController.getDogBreedByName("");
		
		assertNotNull(response);
		assertEquals(400, response.getStatus());		
	}
	
	@Test	
	public void getDogImageByIdentityAndVerifyIfTheDataExists() {
		DogImage dogImg = MockedDogBreedTestData.getDogImage();			
		Mockito.when(dogBreedService.getDogImageByIdentity(dogImg.getImageIdentity())).thenReturn(dogImg);
		
		Response response = dogBreedController.getDogImageByIdentity("identity11");
		
		assertNotNull(response);
		DogImage img = (DogImage) response.getEntity();
		assertNotNull(img);		
		assertEquals("identity11", img.getImageIdentity());	
	}
	
	@Test
	public void missingImageIdentityInputSoReturnBadRequestErrorCode() {
		DogBreed dogBreed = MockedDogBreedTestData.getSingleDogBreed();
		
		List<DogBreed> lDBreeds = new ArrayList<>();
		lDBreeds.add(dogBreed);
		Response response = dogBreedController.getDogImageByIdentity("");
		
		assertNotNull(response);
		assertEquals(400, response.getStatus());		
	}
	
}
