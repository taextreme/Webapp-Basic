<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Login Page</title>
<body>
<p>
    ${error}
</p>
<p>
<form action="/login" method="post">
    <div class="container">
        <label><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" required><br>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required><br>

        <button type="submit">Login</button>
    </div>
</form>
</p>
</body>
</html>