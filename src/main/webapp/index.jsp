<%@ page import="java.util.List" %>

<%
List<String> genres = (List<String>) session.getAttribute("genres");
if (genres == null) {
    response.sendRedirect(request.getContextPath() + "/home");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Movie Recommendation</title>
</head>
<body>

<h2>Genres</h2>

<%
for (String g : genres) {
%>
<form action="<%= request.getContextPath() %>/recommend" method="get">
    <input type="hidden" name="genre" value="<%= g %>">
    <button type="submit"><%= g %></button>
</form>
<%
}
%>

</body>
</html>
