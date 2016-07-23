package services;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CardManager;
import managers.TemplateManager;
import managers.UserManager;
import models.Card;
import models.Template;
import models.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/TemplateService")
public class TemplateService {

    private Gson gson = new GsonBuilder().create();

    @GET
    @Path("/templates/user/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserTemplates(@PathParam("userId") String userId) {
        return gson.toJson(TemplateManager.getUserTemplates());
    }


    @POST
    @Path("/templates/addTemplate")
    @Produces({MediaType.APPLICATION_JSON})
    public String addTemplate(String body) {
        Template template = gson.fromJson(body, Template.class);
        TemplateManager.addTemplate(template);
        return "OK";
    }



    @GET
    @Path("/templates/company/{companyid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCompanyTemplates(@PathParam("companyid") String companyid) {
        return gson.toJson(TemplateManager.gettemplatesForCompany(companyid));
    }


    @GET
    @Path("/templates/deleteTemplate/{templateid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteTemplate(@PathParam("templateid") String templateid) {
        TemplateManager.deleteTemplate(templateid);
        return "OK";
    }

}