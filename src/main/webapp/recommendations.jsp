<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.movierecommendation.model.Movie" %>

<!DOCTYPE html>
<html>
<head>
    <title>Recommended Movies</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background: #020617;
            color: white;
        }

        .page-header {
            padding: 60px 20px;
            text-align: center;
            background: linear-gradient(135deg, #1e293b, #020617);
            animation: fadeIn 1s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(15px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .movie-card {
            background: #0f172a;
            border-radius: 18px;
            overflow: hidden;
            transition: all .35s ease;
        }

        .movie-card:hover {
            transform: translateY(-12px);
            box-shadow: 0 30px 60px rgba(0,0,0,.8);
        }

        .poster {
            height: 420px;
            object-fit: cover;
        }
    </style>
</head>

<body>

<!-- HEADER -->
<div class="page-header">
    <h2 class="fw-bold">🍿 Recommended Movies</h2>
    <p class="text-secondary">Hand-picked based on your choice</p>

    <!-- 🔥 HOME BUTTON -->
    <a href="home" class="btn btn-outline-light mt-3">
        ⬅ Back to Home
    </a>
</div>

<div class="container my-5">
    <div class="row g-4">

        <%
        List<Movie> movies = (List<Movie>) request.getAttribute("movies");

        if (movies == null || movies.isEmpty()) {
        %>
            <div class="text-center text-muted">
                No movies found for this genre
            </div>
        <%
        } else {
            int i = 0;
            for (Movie m : movies) {
        %>

        <div class="col-md-4">
            <div class="movie-card">
                <img src="images/<%= m.getPoster() %>" class="w-100 poster">

                <div class="p-3">
                    <h5 class="fw-bold"><%= m.getTitle() %></h5>
                    <span class="badge bg-warning text-dark mb-2">
                        ⭐ <%= m.getRating() %>
                    </span>
                    <p class="text-secondary small">
                        <%= m.getDescription() %>
                    </p>

                    <button class="btn btn-outline-info w-100"
                            data-bs-toggle="modal"
                            data-bs-target="#trailer<%= i %>">
                        ▶ Watch Trailer
                    </button>
                </div>
            </div>
        </div>

        <!-- MODAL -->
        <div class="modal fade" id="trailer<%= i %>">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content bg-dark">
                    <div class="modal-body p-0">
                        <iframe width="100%" height="420"
                                src="https://www.youtube.com/embed/<%= m.getTeaserId() %>"
                                allowfullscreen>
                        </iframe>
                    </div>
                </div>
            </div>
        </div>

        <%
                i++;
            }
        }
        %>

    </div>
</div>

<footer class="text-center py-3 text-secondary">
    © 2026 Movie Recommendation System
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
