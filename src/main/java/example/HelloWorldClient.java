package example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloWorldClient {

  private static Gson gson = new GsonBuilder().create();

  public static void main(String[] args) {
    Client client = ClientBuilder.newClient();
    String url = "http://localhost:8082/UserService/updateUser";

    User user = new User();
    user.setFirstName("Test22");
    user.setLastName("testtest22");

    user.setEmail("bla@mail.com");
    String body = gson.toJson(user);
    Response simulatorResponse = client.target(url)
            .request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(body, MediaType.APPLICATION_JSON));
  }
}
