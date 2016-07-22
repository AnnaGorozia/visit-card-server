package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CompanyManager;
import managers.UserManager;
import models.Company;
import models.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@WebService
@Path("/UserService")
public class UserService {

  private Gson gson = new GsonBuilder().create();

  @GET
  @Path("/users/email/{email}/password/{password}")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getUsers(@PathParam("email") String email,
                           @PathParam("password") String password) throws Exception{

      boolean succeed = UserManager.logInSuccessed(email, password);

      return Response.status(Response.Status.OK).entity(succeed).build();
  }


  @GET
  @Path("/users/login")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getAuthorization() throws Exception{

    List<User> users = UserManager.getAllUsers();
    return Response.status(Response.Status.OK).entity(gson.toJson(users)).build();

  }



  @GET
  @Path("/users/{userEmail}")
  @Produces({MediaType.APPLICATION_JSON})
  public String getUser(@PathParam("userEmail") String userEmail){
    User user = UserManager.getUserByEmail(userEmail);
    return gson.toJson(user);
  }


  @GET
  @Path("/users/companies/{userId}")
  @Produces({MediaType.APPLICATION_JSON})
  public String getUserCompanies(@PathParam("userId") String userId) {
    List<Company> companies = new ArrayList<Company>();
    List<String> companyIds = UserManager.getUserCompanyIds(userId);
    for (String companyId : companyIds) {
      companies.add(CompanyManager.getCompany(companyId));
    }
    return gson.toJson(companies);
  }

  @POST
  @Path("/registrateUser")
  @Produces({MediaType.APPLICATION_JSON})
  public String addUser(String body) {
    User user = gson.fromJson(body, User.class);
    UserManager.addUser(user);
    return "OK";
  }

  @POST
  @Path("/updateUser")
  @Produces({MediaType.APPLICATION_JSON})
  public String updateUser(String body) {
    User user = gson.fromJson(body, User.class);
    UserManager.updateUser(user);
    return "OK";
  }
}
