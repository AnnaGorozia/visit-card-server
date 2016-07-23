package managers;

import dbconfig.DBConfig;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TemplateManager extends CompanyManager{


    public static ArrayList<Template> getUserTemplates() {

        String query = "select id from templates where owner='user';";

        ArrayList<String> templateids = new ArrayList<String>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                templateids.add(cardid);
            }
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        ArrayList<Template> templates = new ArrayList<Template>();

        int cnt = 0;

        for(String cardid : templateids) {

            templates.add(getTemplate(cardid));
        }


        return templates;
    }


    public static Template getTemplate(String templateid) {


        String query = "select * from field_properties where template_id=" + templateid + ";";

        Template template = new Template();

        template.setId(templateid);

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

                template.addField(field);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return template;
    }


    public static ArrayList<Template> gettemplatesForCompany(String companyid) {
        String query = "select template_id from company_templates where company_id=" + companyid + ";";

        ArrayList<String> templateids = new ArrayList<String>();

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String cardid = resultSet.getString(1);
                templateids.add(cardid);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Template> templates = new ArrayList<Template>();

        for(String templateid : templateids) {
            templates.add(getTemplate(templateid));
        }

        return templates;
    }


    public static int addTemplate(Template template) {
        String query = "insert into templates(width, height, owner) values(50,50,'" + template.getOwner() + "');";


        int cardid = 0;

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
            ResultSet rs  = stmt.executeQuery("SELECT last_insert_id() from templates;");
            rs.next();
            cardid = rs.getInt(1);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Field> fields = template.getFields();

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

    public static void addTemplateForCompany(String companyid, String templateid) {
        try {
            Connection con = DBConfig.getDataSource().getConnection();

            String query = "insert into company_templates(company_id, template_id) values("
                    + companyid + "," + templateid + ");";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addTemplateForCompany(String companyid, Template template) {
        try {

            int templateid = addTemplate(template);

            Connection con = DBConfig.getDataSource().getConnection();

            String query = "insert into company_templates(company_id, template_id) values("
                    + companyid + "," + templateid + ");";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void deleteTemplate(String templateid) {
        try {
            Connection con = DBConfig.getDataSource().getConnection();

            String query = "delete from templates where id=" + templateid + ";";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
