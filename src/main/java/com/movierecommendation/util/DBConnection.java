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

        // 🔍 LOG VALUES (VISIBLE IN RENDER LOGS)
        System.out.println("MYSQL_HOST=" + host);
        System.out.println("MYSQL_PORT=" + port);
        System.out.println("MYSQL_DATABASE=" + db);
        System.out.println("MYSQL_USER=" + user);

        // ❌ FAIL FAST IF ENV VARS ARE MISSING
        if (host == null || db == null || user == null || pass == null || port == null) {
            throw new RuntimeException("❌ MySQL environment variables are missing");
        }

        // ✅ CLEVER CLOUD + RENDER SAFE JDBC URL
        String url =
                "jdbc:mysql://" + host + ":" + port + "/" + db +
                "?useSSL=true" +
                "&requireSSL=true" +
                "&verifyServerCertificate=false" +
                "&allowPublicKeyRetrieval=true" +
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
