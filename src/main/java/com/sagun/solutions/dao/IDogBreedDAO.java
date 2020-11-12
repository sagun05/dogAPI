package com.sagun.solutions.dao;

import java.util.List;

import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;


public interface IDogBreedDAO {
	
	List<DogBreed> getAllDogBreeds();
	
	DogBreed getDogBreedByName(String breed);
	
	void updateDogBreed(DogImage dogImage);
	
	DogImage getDogImageByIdentity(String identity);
}
