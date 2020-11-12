package com.sagun.solutions.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class DogBreed implements Serializable{	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="breed_id")
	private int breed_id;	
	private String breedName;	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	        orphanRemoval = true)
	@JoinColumn(name = "breed_id")
	private List<DogImage> imgList = new ArrayList<>();
	
	public DogBreed() {
		super();
	}
	public DogBreed(String bName) {
		this.breedName = bName;		
	}	

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public int getBreed_id() {
		return breed_id;
	}

	public void setBreed_id(int breed_id) {
		this.breed_id = breed_id;
	}

	public List<DogImage> getImgList() {
		return imgList;
	}
	public void addImage(DogImage image) {
		imgList.add(image);
	}
	public void setImgList(List<DogImage> imgList) {
		this.imgList = imgList;
	}

	@Override
	public String toString() {
		return "DogBreed [breed_id=" + breed_id + ", breedName=" + breedName + ", imgList=" + imgList + "]";
	}
	
	
}
