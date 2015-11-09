package fr.universite.bordeaux.resources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.universite.bordeaux.entities.User;
import fr.universite.bordeaux.repositories.UserRepository;

@Path("/users")
public class UserResource {
	@EJB
	UserRepository userRepository;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> getAllUsers(){
		return userRepository.getAllTheUsers();
	}
	
	@POST
	@Path("/addUser")
	//@Consumes("application/json")
	public void  addUser(@QueryParam("password") String password,@QueryParam("email") String email){
		User user =new User();
		user.setPassword(password);
		user.setEmail(email);
		user.setDateInscription(new Date());
		userRepository.addUser(user);
	}
	
}
