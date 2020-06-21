<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Login Page</title>
<body>
<p>
<form action="/login" method="post">
    <h1>Login</h1>
    <hr>
    <%
        if (request.getAttribute("message") != null) {
    %>
    <div>
        <div class="card-body">
            <%=request.getAttribute("message") %>
        </div>
    </div>
    <%
        }
    %>
    <div class="container">
        <label><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" required><br>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required><br>

        <button type="submit">Login</button>
    </div>
</form>
<a class="nav-link" href="/register">Register</a>
</p>
</body>
</html>