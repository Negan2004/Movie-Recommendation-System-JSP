package com.movierecommendation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String host = System.getenv("MYSQL_HOST");
        String db   = System.getenv("MYSQL_DATABASE");
        String user = System.getenv("MYSQL_USER");
        String pass = System.getenv("MYSQL_PASSWORD");
        String port = System.getenv("MYSQL_PORT");

        // 🔍 DEBUG (TEMPORARY)
        System.out.println("MYSQL_HOST=" + host);
        System.out.println("MYSQL_PORT=" + port);
        System.out.println("MYSQL_DATABASE=" + db);
        System.out.println("MYSQL_USER=" + user);
        
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                     "?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
