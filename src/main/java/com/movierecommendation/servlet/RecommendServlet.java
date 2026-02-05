package com.movierecommendation.servlet;

import com.movierecommendation.util.DBConnection;


import com.movierecommendation.model.Movie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recommend")
public class RecommendServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Movie> movieList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM movies ORDER BY rating DESC"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movie m = new Movie();
                m.setTitle(rs.getString("title"));
                m.setGenre(rs.getString("genre"));
                m.setDescription(rs.getString("description"));
                m.setRating(rs.getDouble("rating"));
                m.setPoster(rs.getString("poster"));
                m.setTeaserId(rs.getString("teaser_id"));
                movieList.add(m);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("movies", movieList);
        RequestDispatcher rd = request.getRequestDispatcher("recommendations.jsp");
        rd.forward(request, response);
    }
}
