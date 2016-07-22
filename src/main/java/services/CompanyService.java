package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CompanyManager;
import models.Company;
import models.User;

import javax.jws.WebService;
import javax.ws.rs.*;
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

    @POST
    @Path("/addCompany")
    @Produces({MediaType.APPLICATION_JSON})
    public String addCompany(String body) {
        Company company = gson.fromJson(body, Company.class);
        CompanyManager.addCompany(company);
        return "OK";
    }

    @POST
    @Path("/addEmployee/{companyId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String addEmployee(@PathParam("companyId") String companyId, String body) {
        User user = gson.fromJson(body, User.class);
        CompanyManager.addEmployee(companyId, user);
        return "OK";
    }


    @GET
    @Path("/companiesById/{companyId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCompanyById(@PathParam("companyId") String companyId){
        Company company = CompanyManager.getCompany(companyId);
        return gson.toJson(company);
    }

    @GET
    @Path("/companiesByMail/{companyMail}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCompanyByMail(@PathParam("companyMail") String companyMail){
        Company company = CompanyManager.getCompany(CompanyManager.getCompanyId(companyMail));
        return gson.toJson(company);
    }

}