package com.sagun.solutions.service;

import java.util.List;

import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;


public interface IDogBreedService {
	
	List<DogBreed> getAllDogBreeds();

	DogBreed getDogBreedByName(String breed);

	void updateDogBreed(DogImage dogImage);
	
	DogImage getDogImageByIdentity(String imageByIdentity);
}
