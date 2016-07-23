package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CardManager;
import managers.UserManager;
import models.Company;
import models.History;
import models.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloWorldClient {

  private static Gson gson = new GsonBuilder().create();
  private static Client client = ClientBuilder.newClient();

  public static void main(String[] args) {

    addCompany();
    addEmployee();
    addHistory();

  }

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
