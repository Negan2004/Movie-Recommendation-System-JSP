package com.movierecommendation.servlet;

import com.movierecommendation.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT username FROM admin WHERE username=? AND password=?"
             )) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Always create a new session
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", rs.getString("username"));
                session.setMaxInactiveInterval(30 * 60); // 30 minutes

                // ✅ ABSOLUTE redirect (cloud safe)
                response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
                return;
            }

            // ❌ Invalid login
            response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
        }
    }
}
