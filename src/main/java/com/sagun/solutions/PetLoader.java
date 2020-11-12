package com.sagun.solutions;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.sagun.solutions.dto.DogBreed;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.context.annotation.Bean;


@Component
public class PetLoader implements InitializingBean {

    private static final Logger LOGGER = getLogger(PetLoader.class);

    // Resources to the different files we need to load.
    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/bulldog.txt")
    private Resource bulldogs;

    @Autowired
    DataSource dataSource;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        loadBreed("Labrador", labradors);
        loadBreed("Pug", pugs);
        loadBreed("Retriever", retrievers);
        loadBreed("Yorkie", bulldogs);
    }
    
    private void loadBreed(String breed, Resource source) throws IOException {
        LOGGER.debug("Loading breed {}", breed);
        JdbcTemplate template = jdbcTemplate();
        template.update("INSERT INTO DOG_BREED (BREED_NAME) VALUES (?)", breed);
        DogBreed dogBreedDB = template.queryForObject("select * from DOG_BREED WHERE BREED_NAME = ?1",
	            new Object[] { breed }, new BeanPropertyRowMapper<>(DogBreed.class));
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;                       
            while ((line = br.readLine()) != null) {
                LOGGER.debug(line);                
                String identity = line.substring(line.length()-11, line.length()-4);
                template.update("INSERT INTO DOG_IMAGE (IMAGE_IDENTITY, IMAGE_URL, VOTE, BREED_ID) VALUES (?, ?, ?, ?)",
            		   identity, line, 0, dogBreedDB.getBreed_id());
            }
        }
    }   
    
   
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }
}
