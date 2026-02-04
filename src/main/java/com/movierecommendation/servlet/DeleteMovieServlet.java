package com.movierecommendation.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/movie_recommendation_db",
                "root",
                "Sam@2004"
            );

            PreparedStatement ps =
                con.prepareStatement("DELETE FROM movies WHERE movie_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();

            response.sendRedirect("adminDashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
