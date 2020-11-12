package com.sagun.solutions.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DogImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="image_id")
	private int image_id;
	
	private String imageUrl;
	private String imageIdentity;	
	private int vote;
	public DogImage() {
		super();
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageIdentity() {
		return imageIdentity;
	}
	public void setImageIdentity(String imageIdentity) {
		this.imageIdentity = imageIdentity;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	@Override
	public String toString() {
		return "DogImage [image_id=" + image_id + ", imageUrl=" + imageUrl + ", imageIdentity=" + imageIdentity
				+ ", vote=" + vote + "]";
	}
}
