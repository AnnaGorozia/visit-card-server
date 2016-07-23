package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CardManager;
import managers.UserManager;
import models.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloWorldClient {

  private static Gson gson = new GsonBuilder().create();
  private static Client client = ClientBuilder.newClient();

  public static void main(String[] args) {

//    addCompany();
//    addEmployee();
//    addHistory();


//      addCard();
//
//      addUserCard();
//
//      addCompanyCard();
//
//      updateCard();

    addTemplate();
    addTemplateForCompanyByid();
    addTempalteForCompanyByObject();
  }

  private static void addTempalteForCompanyByObject() {

    String url = "http://localhost:8082/TemplateService/templates/addTemplateForCompany/company/1";
    Template template = new Template();
    template.setOwner("company");

    String body = gson.toJson(template);

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));



  }

  private static void addTemplateForCompanyByid() {
    String url = "http://localhost:8082/TemplateService/templates/addTemplateForCompany/company/1/tempalte/1";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(null, MediaType.APPLICATION_JSON));

  }

  private static void addTemplate() {
    String url = "http://localhost:8082/TemplateService/templates/addTemplate";

    Template template = new Template();
    template.setOwner("user");

    String body = gson.toJson(template);

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));

  }

  private static void addCompanyCard() {
    // /cards/addCard/company/{companyid}

    Card card = new Card("company_card");

    String body = gson.toJson(card);

    String url = "http://localhost:8082/CardService/cards/company/addCard/1";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));

  }

  private static void addUserCard() {

    // /cards/addUser/user/{userid}

    Card card = new Card("user_card");

    String body = gson.toJson(card);

    String url = "http://localhost:8082/CardService/cards/user/addCard/1";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));

  }

//  private static void addCard() {
//
//    Card card = new Card("path_to_file");
//
//    String body = gson.toJson(card);
//
//    String url = "http://localhost:8082/CardService/cards/addcard";
//
//    Response simulatorResponse = client.target(url)
//            .request(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON)
//            .post(Entity.entity(body, MediaType.APPLICATION_JSON));
//
//
//  }

  private static void addHistory() {
    History history = new History();
    history.setSenderId("1");
    history.setReceiverId("2");
    history.setCardId(CardManager.getCardsForUser("1").get(0).getid());
    String body = gson.toJson(history);

    String url = "http://localhost:8082/CompanyService/addHistory";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));
  }

  private static void addEmployee() {
    User user = UserManager.getUserByEmail("anano@gmail.com");

    String body = gson.toJson(user);

    String url = "http://localhost:8082/CompanyService/addEmployee/1";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));
  }

  private static void addCompany() {
    Company company = new Company();
    company.setName("NewTestCompany");
    String body = gson.toJson(company);

    String url = "http://localhost:8082/CompanyService/addCompany";

    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));
  }
}
