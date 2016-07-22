package managers;

import dbconfig.DBConfig;
import models.Company;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyManager extends ModelManager{


    public static void addCompany(Company company) {
        String query = "insert into companies(name, email, password, address, phone)\n" +
                "values ('" + company.getName() + "','" + company.getEmail() + "','" +
                company.getPassword() + "','" + company.getAddress() + "','" +
                company.getPhone() + "');";


        executeQuery(query);
    }

    public static String getCompanyId(String companyEmail) {
        String companyId= "";

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "select id from companies where email='" + companyEmail + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                companyId = resultSet.getString("id");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companyId;
    }

    public static Company getCompany(String companyid) {
        Company company = null;

        try {
            Connection con = DBConfig.getDataSource().getConnection();
            String query = "SELECT * FROM companies where id='" + companyid + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                company = generateCompany(resultSet);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

    private static Company generateCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setName(resultSet.getString("name"));
        company.setPhone(resultSet.getString("phone"));
        company.setEmail(resultSet.getString("email"));
        company.setPassword(resultSet.getString("password"));
        company.setAddress(resultSet.getString("address"));
        company.setLogo(resultSet.getString("logo"));
        company.setBackground(resultSet.getString("background"));
        return company;
    }

    public static void addEmployee(String companyid, User user) {
        String query = "insert into company_employees(company_id, user_id)\n" +
                "values ('" + companyid + "','" +
                user.getid() + "');";
        executeQuery(query);
    }

}
