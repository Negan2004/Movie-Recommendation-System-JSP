<%@ page import="java.sql.*" %>
<%@ page import="com.movierecommendation.util.DBConnection" %>

<%
if (session == null || session.getAttribute("admin") == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
}

Connection con = null;
try {
    con = DBConnection.getConnection();
} catch (Exception e) {
    out.println("DB Error: " + e.getMessage());
}
%>


<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-4">

<!-- ================= HEADER ================= -->
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="bi bi-speedometer2"></i> Admin Dashboard</h2>
    <a href="logout" class="btn btn-outline-danger">
        <i class="bi bi-box-arrow-right"></i> Logout
    </a>
</div>

<!-- ================= ADD GENRE ================= -->
<div class="card shadow-sm mb-4">
    <div class="card-header fw-bold">
        <i class="bi bi-tags"></i> Add New Genre
    </div>
    <div class="card-body">
        <form method="post" action="addGenre" class="d-flex gap-2">
            <input class="form-control" name="genre_name"
                   placeholder="Enter genre (eg Thriller)" required>
            <button class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Add
            </button>
        </form>
    </div>
</div>

<!-- ================= ADD MOVIE ================= -->
<div class="card shadow-sm mb-4">
    <div class="card-header fw-bold">
        <i class="bi bi-film"></i> Add New Movie
    </div>
    <div class="card-body">

        <form method="post" action="addMovie">
            <input class="form-control mb-2" name="title" placeholder="Movie Title" required>

            <select class="form-control mb-2" name="genre" required>
                <option value="">Select Genre</option>
                <%
                Statement gst = con.createStatement();
                ResultSet grs = gst.executeQuery("SELECT genre_name FROM genres");
                while (grs.next()) {
                %>
                    <option><%= grs.getString("genre_name") %></option>
                <%
                }
                grs.close();
                gst.close();
                %>
            </select>

            <textarea class="form-control mb-2" name="description"
                      placeholder="Movie Description" rows="3" required></textarea>

            <input class="form-control mb-2" name="rating" placeholder="Rating (eg 4.5)" required>
            <input class="form-control mb-2" name="poster" placeholder="Poster file name" required>
            <input class="form-control mb-3" name="teaser_id" placeholder="YouTube Teaser ID" required>

            <button class="btn btn-success">
                <i class="bi bi-plus-lg"></i> Add Movie
            </button>
        </form>

    </div>
</div>

<!-- ================= UPDATE MOVIE ================= -->
<div class="card shadow-sm mb-4">
    <div class="card-header fw-bold">
        <i class="bi bi-pencil-square"></i> Update Movie
    </div>
    <div class="card-body">

        <form method="post" action="updateMovie">
            <input class="form-control mb-2" name="movie_id"
                   placeholder="Movie ID (auto-filled from list)" required>

            <textarea class="form-control mb-2" name="description"
                      placeholder="New Description"></textarea>

            <input class="form-control mb-2" name="rating" placeholder="New Rating">
            <input class="form-control mb-2" name="poster" placeholder="New Poster file">
            <input class="form-control mb-3" name="teaser_id" placeholder="New Teaser ID">

            <button class="btn btn-warning">
                <i class="bi bi-arrow-repeat"></i> Update Movie
            </button>
        </form>

    </div>
</div>

<!-- ================= SEARCH ================= -->
<div class="card shadow-sm mb-4">
    <div class="card-header fw-bold">
        <i class="bi bi-search"></i> Search Movie
    </div>
    <div class="card-body">
        <form method="get" action="adminDashboard.jsp">
            <input type="text" name="search" class="form-control"
                   placeholder="Search by movie title"
                   value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
        </form>
    </div>
</div>

<!-- ================= MOVIE LIST ================= -->
<div class="card shadow-sm mb-5">
    <div class="card-header fw-bold">
        <i class="bi bi-list-ul"></i> Movie List
    </div>
    <div class="card-body p-0">

        <table class="table table-striped table-hover mb-0">
            <tr class="table-dark">
                <th>ID</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Actions</th>
            </tr>

            <%
            Statement st = con.createStatement();
            String search = request.getParameter("search");
            String sql = "SELECT movie_id, title, genre FROM movies";

            if (search != null && !search.trim().isEmpty()) {
                sql += " WHERE title LIKE '%" + search + "%'";
            }

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("movie_id") %></td>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("genre") %></td>
                <td>
                    <button class="btn btn-sm btn-warning"
                            onclick="document.querySelector('[name=movie_id]').value='<%= rs.getInt("movie_id") %>'">
                        <i class="bi bi-pencil"></i> Edit
                    </button>

                    <a href="deleteMovie?id=<%= rs.getInt("movie_id") %>"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Delete this movie?')">
                        <i class="bi bi-trash"></i> Delete
                    </a>
                </td>
            </tr>
            <%
            }
            rs.close();
            st.close();
            con.close();
            %>

        </table>

    </div>
</div>

</div>

<!-- FOOTER -->
<footer class="bg-dark text-light text-center py-3">
    © 2026 Movie Recommendation System | Java · JSP · MySQL
</footer>

</body>
</html>
