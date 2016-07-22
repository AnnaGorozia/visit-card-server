package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.HistoryManager;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

}