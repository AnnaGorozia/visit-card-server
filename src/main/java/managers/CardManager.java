package managers;

import dbconfig.DBConfig;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardManager extends CompanyManager{


    public static ArrayList<Card> getCardsForUser(String userid) {

        String query = "select card_id from user_cards where user_id='" + userid + "';";

        ArrayList<String> result = new ArrayList<String>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                result.add(cardid);
            }
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        ArrayList<Card> cardsList = new ArrayList<Card>();

        int cnt = 0;

        for(String cardid : result) {

            cardsList.add(getCard(cardid));
        }


        return cardsList;
    }

    public static Card getCard(String cardid) {


        String query = "select * from field_properties where card_id=" + cardid + ";";

        Card card = new Card(cardid);


        try {

            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int field_name_id = resultSet.getInt(2);
                int location_x = resultSet.getInt(4);
                int location_y = resultSet.getInt(5);
                String field_value = resultSet.getString(6);
                int field_color_id = resultSet.getInt(7);
                int field_font_id = resultSet.getInt(8);
                int font_size = resultSet.getInt(9);

                /* double xCoordinate, double yCoordinate, FieldType fieldType, String fieldValue,
                String color, String font, String fontSize */

                FieldType fieldType = FieldType.getFieldType(field_name_id);
                Color color = Color.getColor(field_color_id);
                Font font = Font.getFont(field_font_id);

                Field field = new Field(location_x, location_y, fieldType, field_value, color, font, font_size);

                card.addField(field);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }

    public static ArrayList<Card> getCardsForCompany(String companyid) {
        String query = "select card_id from company_cards where company_id='" + companyid + "';";

        ArrayList<String> result = new ArrayList<String>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                result.add(cardid);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Card> cardsList = new ArrayList<Card>();

        for(String cardid : result) {
            cardsList.add(getCard(cardid));
        }

        return cardsList;
    }

    public static int addCard(Card card) {
        String query = "insert into cards(width, height) values(50,50);";

        int cardid = 0;

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            cardid = stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Field> fields = card.getFields();

        for(Field field : fields) {


            String fieldNameid = "" + FieldType.getFieldTypeid(field.getFieldType());
            String location_x = "" + field.getxCoordinate();
            String location_y = "" + field.getyCoordinate();
            String fieldValue = field.getFieldValue();
            String fieldColor = "" + Color.getColorid(field.getColor());
            String fieldFont = "" + Font.getfontid(field.getFont());
            String fontSize = "" + field.getFontSize();

            query = "insert into field_properties(field_name_id, card_id, location_x, location_y, field_value, field_color_id," +
                    "field_font_id, font_size) values('" + fieldNameid + "'," + cardid + "," + location_x + "," + location_y +
                    ",'" + fieldValue + "'," + fieldColor + "," + fieldFont + "," + fontSize + ");";


            try {
                Connection con = DBConfig.getDataSource().getConnection();
                PreparedStatement stmt = con.prepareStatement(query);
                cardid = stmt.executeUpdate();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public static void updateCard(Card card) {


        try {
            Connection con = DBConfig.getDataSource().getConnection();

            for(Field field: card.getFields()) {

                String query = "update field_properties set field_value='" + field.getFieldValue() + "" +
                        "' where id = "  + card.getid() + ";";



                PreparedStatement stmt = con.prepareStatement(query);
                stmt.executeUpdate();
            }
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
