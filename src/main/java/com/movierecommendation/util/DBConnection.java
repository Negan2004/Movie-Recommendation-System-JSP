package com.movierecommendation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String host = System.getenv("btnsj9lhwbgxt0ungzqk-mysql.services.clever-cloud.com\r\n"
        		+ "");
        String db   = System.getenv("btnsj9lhwbgxt0ungzqk\r\n"
        		+ "");
        String user = System.getenv("ulr4ple07ls3idul\r\n"
        		+ "");
        String pass = System.getenv("2WaPzfow5IeTyaF7FETR\r\n"
        		+ "");
        String port = System.getenv("3306\r\n"
        		+ "");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                     "?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
