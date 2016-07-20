package example;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebService
@Path("/users")
public class HelloWorld {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessage() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/evc_db?characterEncoding=UTF8");
    ds.setUsername("root");
    ds.setPassword("root");
    String res = "";

    try {
      Connection con = ds.getConnection();
      String query = "SELECT * FROM users";
      PreparedStatement stmt = con.prepareStatement(query);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        res += "user_name: " + resultSet.getString("user_name") + " \n";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }
}