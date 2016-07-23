package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CardManager;
import managers.UserManager;
import models.Card;
import models.User;

import javax.jws.WebService;
import javax.ws.rs.*;
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


    @POST
    @Path("/cards/addcard")
    @Produces({MediaType.APPLICATION_JSON})
    public String addCard(String body) {
        Card card = gson.fromJson(body, Card.class);
        CardManager.addCard(card);
        return "OK";
    }



    @GET
    @Path("/cards/company/{companyid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCompanyCards(@PathParam("companyid") String companyid) {
        return gson.toJson(CardManager.getCardsForCompany(companyid));
    }

    @GET
    @Path("/cards/user/{userid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String addCardForUser(@PathParam("userid") String userid,
                                 String body) {

        CardManager.addCardForUser(userid, gson.fromJson(body, Card.class));
        return "OK";
    }

    @GET
    @Path("/cards/company/{companyid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String addCardForCompany(@PathParam("companyid") String userid,
                                 String body) {

        CardManager.addCardForCompany(userid, gson.fromJson(body, Card.class));
        return "OK";
    }


    @GET
    @Path("/cards/deleteCard/{cardid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteCard(@PathParam("companyid") String cardid) {
        CardManager.deleteCard(cardid);
        return "OK";
    }

}
