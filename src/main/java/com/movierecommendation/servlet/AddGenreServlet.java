package com.movierecommendation.servlet;

import com.movierecommendation.util.DBConnection;


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addGenre")
public class AddGenreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String genreName = request.getParameter("genre_name");

        try {
        	Connection con = DBConnection.getConnection();


            PreparedStatement ps =
                con.prepareStatement("INSERT INTO genres (genre_name) VALUES (?)");

            ps.setString(1, genreName);
            ps.executeUpdate();

            con.close();
            response.sendRedirect("adminDashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp");
        }
    }
}
