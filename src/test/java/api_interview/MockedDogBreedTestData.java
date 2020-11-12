package api_interview;

import java.util.ArrayList;
import java.util.List;

import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;

public class MockedDogBreedTestData {
	
	public static List<DogBreed> getMultipleDogBreeds() {
		List<DogBreed> lDogBreed = new ArrayList<>();
		
		DogBreed dogBreedA = new DogBreed();
		dogBreedA.setBreed_id(1);
		dogBreedA.setBreedName("abc");
		
		DogImage dogImage = new DogImage();
		dogImage.setImage_id(200);
		dogImage.setImageIdentity("identity1");
		dogImage.setImageUrl("http1");
		dogImage.setVote(1);
		dogBreedA.addImage(dogImage);
		
		lDogBreed.add(dogBreedA);
		
		DogBreed dogBreedB = new DogBreed();
		dogBreedB.setBreed_id(2);
		dogBreedB.setBreedName("abcB");
		
		DogImage dogImageB = new DogImage();
		dogImageB.setImage_id(201);
		dogImageB.setImageIdentity("identity2");
		dogImageB.setImageUrl("http2");
		dogImageB.setVote(0);
		dogBreedB.addImage(dogImageB);
		
		lDogBreed.add(dogBreedB);
		
		return lDogBreed;		
	}
	
	public static DogBreed getSingleDogBreed() {
		
		DogBreed dogBreedA = new DogBreed();
		dogBreedA.setBreed_id(1);
		dogBreedA.setBreedName("abc");
		
		DogImage dogImage = new DogImage();
		dogImage.setImage_id(200);
		dogImage.setImageIdentity("identity1");
		dogImage.setImageUrl("http1");
		dogImage.setVote(1);
		dogBreedA.addImage(dogImage);
		return dogBreedA;
		
	}
	
	public static DogImage getDogImage() {
		DogImage dogImage = new DogImage();
		dogImage.setImage_id(300);
		dogImage.setImageIdentity("identity11");
		dogImage.setImageUrl("http11");
		dogImage.setVote(1);
		return dogImage;
		
	}
}
