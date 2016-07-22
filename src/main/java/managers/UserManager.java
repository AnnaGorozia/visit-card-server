package managers;

import dbconfig.DBConfig;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager extends ModelManager {

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "SELECT * FROM users";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = generateUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static User getUserByEmail(String userEmail) {
        User user = null;

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "SELECT * FROM users where email='" + userEmail + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                user = generateUser(resultSet);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void addUser(User user) {

        String query = "insert into users(first_name, last_name, image, email, password, phone) " +
                        "values('" + user.getFirstName() + "','" + user.getLastName() + "','" +
                        user.getImage() + "','" + user.getEmail() + "','" + user.getPassword() + "','" +
                        user.getPhone() + "');";

        executeQuery(query);

    }

    public static  void addUsers(ArrayList<User> usersList) {

        for(User user : usersList) {
            addUser(user);
        }
    }

    public static void updateUser(User user) {

        String query = "update users set first_name='" + user.getFirstName() + "',last_name='" +
                user.getLastName() + "',image='" + user.getImage() + "',password='" + user.getPassword() +
                "',phone='" + user.getPhone() + "' where email='" + user.getEmail() + "';";


        executeQuery(query);
    }

    public static ArrayList<String> getUserCompanyIds(String userid) {

        ArrayList<String> result = new ArrayList<String>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "SELECT company_id from users, company_employees where users.id=user_id";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String companyid = resultSet.getString(1);
                result.add(companyid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    private static User generateUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone( resultSet.getString("phone"));
        return user;
    }
}
