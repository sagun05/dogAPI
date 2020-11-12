package com.sagun.solutions.service;

import com.sagun.solutions.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sagun.solutions.dao.DogBreedDAO;
import java.util.List;

@Service
public class DogBreedService implements IDogBreedService {
	
	@Autowired
	private DogBreedDAO dogBreedDAO;
		
	@Override
	public List<DogBreed> getAllDogBreeds() {
		List<DogBreed> lBreeds = dogBreedDAO.getAllDogBreeds();
		return lBreeds;
	}
	
	@Override
	public DogBreed getDogBreedByName(String breed) {
		return dogBreedDAO.getDogBreedByName(breed);
	}

	@Override
	public synchronized void updateDogBreed(DogImage dogImage) {
		dogBreedDAO.updateDogBreed(dogImage);
	}

	@Override
	public DogImage getDogImageByIdentity(String imageByIdentity) {
		return dogBreedDAO.getDogImageByIdentity(imageByIdentity);
	}

}
