package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CompanyManager;
import models.Company;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
@Path("/CompanyService")
public class CompanyService {

    private Gson gson = new GsonBuilder().create();

    @GET
    @Path("/companies")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCompanies() throws Exception{
        List<Company> companies = CompanyManager.getAllCompanies();
        return Response.status(Response.Status.OK).entity(gson.toJson(companies)).build();

    }

}