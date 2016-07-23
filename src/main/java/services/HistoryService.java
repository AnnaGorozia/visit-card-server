package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CompanyManager;
import managers.HistoryManager;
import models.Company;
import models.History;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/HistoryService")
public class HistoryService {

    private Gson gson = new GsonBuilder().create();

    @GET
    @Path("/histories/user/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserHistories(@PathParam("userId") String userId) {
        return gson.toJson(HistoryManager.getUserHistory(userId));
    }

    @GET
    @Path("/histories/received/user/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserReceivedHistories(@PathParam("userId") String userId) {
        return gson.toJson(HistoryManager.getUserReceivedHistory(userId));
    }

    @GET
    @Path("/histories/company/{companyId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCompanyHistory(@PathParam("companyId") String companyId) {
        return gson.toJson(HistoryManager.getCompanyHistory(companyId));
    }

    @POST
    @Path("/addHistory")
    @Produces({MediaType.APPLICATION_JSON})
    public String addHistory(String body) {
        System.out.println(body);
        History history = gson.fromJson(body, History.class);
        HistoryManager.addHistory(history.getSenderId(), history.getReceiverId(), history.getCardId());
        return "OK";
    }

}