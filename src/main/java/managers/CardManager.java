package managers;

import dbconfig.DBConfig;
import models.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by khrak on 7/23/16.
 */
public class CardManager {

    public static ArrayList<Card> getCardsForUser(String userid) {

        String query = " select cards.id, cards.path from cards, user_cards where " +
                "cards.id=user_cards.card_id and user_cards.user_id=" + userid + ";";

        ArrayList<Card> result = new ArrayList<Card>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                String cardpath = resultSet.getString(2);
                Card card = new Card(cardid, cardpath);
                result.add(card);
            }
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result;
    }


    public static Card getCard(String cardid) {

        String query = "select * from cards where id=" + cardid + ";";

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String path = resultSet.getString(2);
                Card card = new Card(id, path);
                return card;
            }
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    public static ArrayList<Card> getCardsForCompany(String companyid) {
        String query = " select cards.id, cards.path from cards, company_cards where " +
                "cards.id=company_cards.card_id and company_cards.company_id=" + companyid + ";";

        ArrayList<Card> result = new ArrayList<Card>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                String cardpath = resultSet.getString(2);
                Card card = new Card(cardid, cardpath);
                result.add(card);
            }
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result;
    }

    public static int addCard(Card card) {
        String query = "insert into cards(path) values('" + card + "');";

        int cardid = 0;

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            cardid = stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cardid;
    }

    public static void addCardForUser(String userid, Card card) {

        int cardid = addCard(card);

        String query = "insert into user_cards(user_id, card_id) values(" + userid + "," + cardid + ");";

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            cardid = stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addCardForCompany(String companyid, Card card) {
        int cardid = addCard(card);

        String query = "insert into company_cards(company_id, card_id) values(" + companyid + "," + cardid + ");";

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            cardid = stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCard(String cardid) {
        try {
            Connection con = DBConfig.getDataSource().getConnection();

            String query = "delete from cards where id=" + cardid + ";";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
