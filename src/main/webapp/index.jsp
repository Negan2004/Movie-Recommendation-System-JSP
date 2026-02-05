<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>

<%
/* 🚫 Block direct access — must come via /home servlet */
List<String> genres = (List<String>) request.getAttribute("genres");
if (genres == null) {
    response.sendRedirect(request.getContextPath() + "/home");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Movie Recommendation System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background: #0f172a;
            color: #ffffff;
        }

        /* HERO */
        .hero {
            min-height: 70vh;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            background: radial-gradient(circle at top, #1e3a8a, #020617);
            animation: fadeIn 1.2s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* GENRE CARD */
        .genre-card {
            background: linear-gradient(145deg, #111827, #020617);
            border-radius: 18px;
            padding: 30px;
            text-align: center;
            transition: all .35s ease;
            cursor: pointer;
            color: #ffffff;
        }

        .genre-card h5 {
            color: #ffffff;
        }

        .genre-card:hover {
            transform: translateY(-15px) scale(1.05);
            box-shadow: 0 30px 60px rgba(0,0,0,.7);
        }

        .genre-icon {
            font-size: 3.2rem;
            color: #38bdf8;
        }

        footer {
            background: #020617;
        }
    </style>
</head>

<body>

<!-- HERO -->
<section class="hero">
    <div>
        <h1 class="display-4 fw-bold">🎬 Movie Recommendation System</h1>
        <p class="lead mt-3 text-light">
            Get personalized movie suggestions instantly
        </p>
        <p class="text-secondary">Choose a genre to continue</p>
    </div>
</section>

<!-- GENRES -->
<section class="container my-5">
    <h3 class="text-center fw-bold mb-5">🎥 Browse by Genre</h3>

    <div class="row g-4 justify-content-center">

        <%
        if (!genres.isEmpty()) {
            for (String genre : genres) {
        %>

        <div class="col-md-3 col-sm-6">
            <form action="<%= request.getContextPath() %>/recommend" method="get">
                <input type="hidden" name="genre" value="<%= genre %>">

                <button type="submit" class="w-100 bg-transparent border-0">
                    <div class="genre-card">
                        <i class="bi bi-film genre-icon"></i>
                        <h5 class="mt-3"><%= genre %></h5>
                        <span class="badge bg-info mt-2">View Movies</span>
                    </div>
                </button>
            </form>
        </div>

        <%
            }
        } else {
        %>
            <p class="text-center text-warning">
                No genres found. Please add genres from Admin Dashboard.
            </p>
        <%
        }
        %>

    </div>
</section>

<footer class="text-center py-3">
    © 2026 Movie Recommendation System | Java · JSP · MySQL
</footer>

</body>
</html>
