package managers;

import dbconfig.DBConfig;
import models.History;

/*
 * Created by Anna on 7/21/2016.
 */

import java.sql.*;
import java.util.ArrayList;

public class HistoryManager extends ModelManager{


    public static void addHistory(String senderId, String receivedId, String cardId) {
        String query = "insert into users(sender_id, receiver_id, card_id, date) " +
                "values('" + senderId + "','" + receivedId + "','" + cardId + "','" +
                new Date(System.currentTimeMillis()) + "');";

        executeQuery(query);

    }

    public ArrayList<History> getUserHistory(String userId) {
        ArrayList<History> histories = new ArrayList<History>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "SELECT * FROM histories where sender_id='" + userId + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                History history = generateHistory(resultSet);
                histories.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return histories;
    }

    public ArrayList<History> getCompanyHistory(String companyId) {

        return null;
    }


    private History generateHistory(ResultSet resultSet) throws SQLException {
        History history = new History();
        history.setSenderId(resultSet.getString("sender_id"));
        history.setReceiverId(resultSet.getString("receiver_id"));
        history.setCardId(resultSet.getString("card_id"));
        history.setDate(resultSet.getDate("date"));
        return history;
    }

}
