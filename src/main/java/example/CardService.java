package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CardManager;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/CardService")
public class CardService {

    private Gson gson = new GsonBuilder().create();

    @GET
    @Path("/cards/user/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserCards(@PathParam("userId") String userId) {
        return gson.toJson(CardManager.getCardsForUser(userId));
    }

}