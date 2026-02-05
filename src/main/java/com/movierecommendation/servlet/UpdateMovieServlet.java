package com.movierecommendation.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movierecommendation.util.DBConnection;

@WebServlet("/updateMovie")
public class UpdateMovieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int movieId = Integer.parseInt(request.getParameter("movie_id"));

        String description = request.getParameter("description");
        String ratingStr   = request.getParameter("rating");
        String poster      = request.getParameter("poster");
        String teaserId    = request.getParameter("teaser_id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DBConnection.getConnection();


            StringBuilder sql = new StringBuilder("UPDATE movies SET ");
            List<Object> params = new ArrayList<>();

            if (description != null && !description.trim().isEmpty()) {
                sql.append("description=?, ");
                params.add(description);
            }

            if (ratingStr != null && !ratingStr.trim().isEmpty()) {
                sql.append("rating=?, ");
                params.add(Double.parseDouble(ratingStr));
            }

            if (poster != null && !poster.trim().isEmpty()) {
                sql.append("poster=?, ");
                params.add(poster);
            }

            if (teaserId != null && !teaserId.trim().isEmpty()) {
                sql.append("teaser_id=?, ");
                params.add(teaserId);
            }

            // ⚠️ Nothing to update
            if (params.isEmpty()) {
                response.sendRedirect("adminDashboard.jsp");
                return;
            }

            sql.setLength(sql.length() - 2); // remove last comma
            sql.append(" WHERE movie_id=?");
            params.add(movieId);

            PreparedStatement ps = con.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ps.executeUpdate();
            con.close();

            response.sendRedirect("adminDashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp");
        }
    }
}
