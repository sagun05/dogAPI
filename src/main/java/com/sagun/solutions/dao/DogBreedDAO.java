package com.sagun.solutions.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sagun.solutions.dto.DogBreed;
import com.sagun.solutions.dto.DogImage;


@Repository
public class DogBreedDAO implements IDogBreedDAO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate JdbcTemplate;	
	public final static RowMapper<DogBreed> breedMapper = BeanPropertyRowMapper.newInstance(DogBreed.class);
    public final static RowMapper<DogImage> imageMapper = BeanPropertyRowMapper.newInstance(DogImage.class);    
	
	
	@Override
	public List<DogBreed> getAllDogBreeds() {
		log.debug("Finding all dog breeds!!!");		
		return findAllDogBreedsWithImages(); 
	}
	
	@Override
	public DogBreed getDogBreedByName(String breed) {
		log.debug("Finding particular dog breed by breed!!!");
		return findDogBreedWithImages(breed);	
	}

	@Override
	public synchronized void updateDogBreed(DogImage dogImage) {
		log.debug("Querying dog image with ImageIdentity: " + dogImage.getImageIdentity());
		DogImage dogImageDB = JdbcTemplate.queryForObject("select * from DOG_IMAGE WHERE IMAGE_IDENTITY = ?1 order by VOTE DESC",
	            new Object[] { dogImage.getImageIdentity() }, new BeanPropertyRowMapper<>(DogImage.class));
		if(null != dogImageDB) {			
			//Vote up and down
			int dbVote=dogImageDB.getVote();
			if(dogImage.getVote() == 0 && dbVote != 0) {
				dbVote = dbVote-1;
			}
			if(dogImage.getVote() == 1) {
				dbVote = dbVote+1;
			}
			//end
			log.debug("before Vote Update, repository vote value is: " + dbVote);
			JdbcTemplate.update("UPDATE DOG_IMAGE SET VOTE = ? WHERE IMAGE_ID = ?",
					dbVote, dogImageDB.getImage_id());	
			log.info("Dog Image Vote Updated!!!");
		} else {
			log.info("Dog Image not Found!!!");
		}
		
	}
	
	private List<DogBreed> findAllDogBreedsWithImages() {
        return JdbcTemplate.query("select * from dog_breed, dog_image "
                + " where dog_breed.breed_id = dog_image.breed_id order by dog_breed.breed_name, dog_image.vote desc",
                new ResultSetExtractor<List<DogBreed>>() {
                    public List<DogBreed> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<DogBreed> breeds = new ArrayList<DogBreed>();
                        Long breedId = null;
                        DogBreed currentBreed = null;
                        int breedIdx = 0;
                        int imageIdx = 0;
                        while (rs.next()) {
                            // first row or when dog breed changes
                            if (currentBreed == null || !breedId.equals(rs.getLong("breed_id"))) {
                            	breedId = rs.getLong("breed_id");
                                currentBreed = breedMapper.mapRow(rs, breedIdx++);
                                imageIdx = 0;
                                breeds.add(currentBreed);
                            }
                            currentBreed.addImage(imageMapper.mapRow(rs, imageIdx++));
                        }
                        return breeds;
                    }

                });
    }
	
	private DogBreed findDogBreedWithImages(String breed) {
        return JdbcTemplate.query("select * from dog_breed, dog_image "
                + " where dog_breed.breed_id = dog_image.breed_id and dog_breed.breed_name = ? order by dog_image.vote desc", 
                new ResultSetExtractor<DogBreed>() {
            public DogBreed extractData(ResultSet rs) throws SQLException, DataAccessException {
            	DogBreed dogBreed = null;
                int row = 0;
                while (rs.next()) {
                    if (dogBreed == null) {
                    	dogBreed = breedMapper.mapRow(rs, row);
                    }
                    dogBreed.addImage(imageMapper.mapRow(rs, row));
                    row++;
                }
                return dogBreed;
            }

        }, breed);
    }

	@Override
	public DogImage getDogImageByIdentity(String identity) {
		log.debug("Querying dog image with ImageIdentity: " + identity);
		DogImage dogImageDB = JdbcTemplate.queryForObject("select * from DOG_IMAGE WHERE IMAGE_IDENTITY = ?1 order by VOTE DESC",
	            new Object[] { identity }, new BeanPropertyRowMapper<>(DogImage.class));
		return dogImageDB;
	}
}
