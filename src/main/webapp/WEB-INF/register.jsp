<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Register Page</title>
<body>
<p>
<form action="/register" method="post">
    <h1>Register</h1>
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

        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required><br>

        <button type="submit">Submit</button>
    </div>

</form>
<a class="nav-link" href="/">Leave</a>
</p>
</body>
</html>