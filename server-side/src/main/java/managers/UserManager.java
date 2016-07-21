package managers;

import dbconfig.DBConfig;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

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

    public void addUser(User user) {}

    public String getUserIDByEmail(String userEmail) {return null; }

    public void addUsers(ArrayList<User> usersList) {}

    public void updateFirstName(String userid, String newName) {}

    public void updateLastName(String userid, String lastName) {}

    public void updateImage(String userid, String newImage) {}

    public void updatePassword(String userid, String newPassword) {}

    public void updatePhone(String userid, String newPhone) {}

    public String getUserCompanyId(String userid) { return null; }


    private static User generateUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setMainMail(resultSet.getString("main_email"));
        user.setPhone( resultSet.getString("phone"));
        return user;
    }

}
