<%@ page import="io.muzoo.ooc.webapp.basic.database.MySQLJava" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Home Page</title>
<body>
<p>
<h1>Home</h1>
<hr>
<h2>
    Hello ${username}
</h2>
<a class="button" href="/logout">Logout</a><br>
<br>
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
<br>
<div class="container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody><%
            ResultSet rs = MySQLJava.getInstance().getUsersData();
            while (rs.next()) {
                try {
                    String usernameEntry = rs.getString("username");
                    String nameEntry = rs.getString("name");
                    int idEntry = rs.getInt("id");
        %>
        <tr>
            <td><%= idEntry %>
            </td>
            <td><%= usernameEntry %>
            </td>
            <td><%= nameEntry %>
            </td>
            <%
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            %>
        </tr>
        </tbody>
    </table>
    <br>
    <div>
        <label><b>Edit User</b></label>
        <form action="/edituser" method="post">
            <div class="modal-body">
                <div class="form-group">
                    <label for="id-edit" class="col-form-label"> id:</label>
                    <input type="text" class="form-control" id="id-edit" name="id-edit">
                </div>
                <div class="form-group">
                    <label for="username-edit" class="col-form-label"> New username:</label>
                    <input type="text" class="form-control" id="username-edit" name="username-edit">
                </div>
                <div class="form-group">
                    <label for="password-edit" class="col-form-label"> New password:</label>
                    <input type="password" class="form-control" id="password-edit" name="password-edit">
                </div>
                <div class="form-group">
                    <label for="name-edit" class="col-form-label">New name:</label>
                    <input type="text" class="form-control" id="name-edit" name="name-edit">
                </div>
                <button type="submit" class="btn btn-primary">Edit user</button>
            </div>
        </form>
    </div>
</div>
</p>
</body>
</html>
