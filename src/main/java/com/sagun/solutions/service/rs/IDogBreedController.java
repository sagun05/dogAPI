package com.sagun.solutions.service.rs;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import com.sagun.solutions.dto.DogImage;
/**
 * @author sagun
 *
 */
@Path("/service")
@Produces({MediaType.APPLICATION_JSON})
public interface IDogBreedController {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/dogbreeds")
	public Response getAllDogBreeds();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/dogbreed/{name}")
	public Response getDogBreedByName(@PathParam("name") String name);
	

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
	@Path("/votedogimage")
	public Response voteDogImage(@RequestBody DogImage dogImage);
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/dogimage/{imageIdentity}")
	public Response getDogImageByIdentity(@PathParam("imageIdentity") String imageIdentity);
	
	
}
