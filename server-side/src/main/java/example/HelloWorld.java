package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.UserManager;
import models.User;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class HelloWorld {

  private Gson gson = new GsonBuilder().create();

  @GET
  public Response getMessage() throws Exception{

    List<User> users = UserManager.getAllUsers();
    return Response.status(Response.Status.OK).entity(gson.toJson(users)).build();

  }


}