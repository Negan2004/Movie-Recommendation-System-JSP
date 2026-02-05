package com.movierecommendation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws Exception {

	    String url =
	        "jdbc:mysql://btnsj9lhwbgxt0ungzqk-mysql.services.clever-cloud.com:3306/"
	        + "btnsj9lhwbgxt0ungzqk"
	        + "?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	    String user = "ulr4ple07ls3idul";
	    String pass = "2WaPzfow5IeTyaF7FETR";

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(url, user, pass);
	}

}
