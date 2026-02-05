package com.movierecommendation.servlet;

import com.movierecommendation.util.DBConnection;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO movies (title, genre, description, rating, poster, teaser_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, request.getParameter("title"));
            ps.setString(2, request.getParameter("genre"));
            ps.setString(3, request.getParameter("description"));
            ps.setDouble(4, Double.parseDouble(request.getParameter("rating")));
            ps.setString(5, request.getParameter("poster"));
            ps.setString(6, request.getParameter("teaser_id"));

            ps.executeUpdate();
            con.close();

            response.sendRedirect("adminDashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
