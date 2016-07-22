package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CompanyManager;
import managers.UserManager;
import models.Company;
import models.User;

import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebService
@Path("/UserService")
public class HelloWorld {

  private Gson gson = new GsonBuilder().create();

  @GET
  @Path("/users")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getUsers() throws Exception{

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
  
}