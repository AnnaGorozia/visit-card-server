package managers;

import dbconfig.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ModelManager {

    public static void executeQuery(String query) {
        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            int result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
