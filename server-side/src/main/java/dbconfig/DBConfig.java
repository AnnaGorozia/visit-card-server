package dbconfig;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConfig {

    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/evc_db?characterEncoding=UTF8");
            dataSource.setUsername("root");
            dataSource.setPassword("root");

        }
        return dataSource;
    }
}
