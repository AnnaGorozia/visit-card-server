package example;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceProvider;

@WebService
@Path("/hello")
public class HelloWorld {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessage() {
    return "Hello world!";
  }
}