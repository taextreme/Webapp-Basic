<%@ page import="io.muzoo.ooc.webapp.basic.database.MySQLJava" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Home Page</title>
<body>
<p>
<h1>Home</h1>
<hr>
<h2>Hello ${name}</h2>
<div>Username: ${username}</div>
<div>ID: ${sessionId}</div>
<br>
<a class="nav-link" href="/register">Register</a><br>
<a class="nav-link" href="/logout">Logout</a><br>
<br>
<style>
    table, th, td {
        border: 1px solid black;
    }

    h2, h3, p {
        margin: 0;
    }
</style>
<div class="container">
    <table>
        <thead>
        <h3>Users Table</h3>
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
            <td>
                <% if (idEntry != (Integer) request.getSession().getAttribute("sessionId")) { %>

                <div>
                    <form action="/removeuser" method="post">
                        <button type="submit"
                                onclick="return confirm('Are you sure you want to Remove <%= nameEntry%>?');">Remove
                        </button>
                        <input type="hidden" id="id-to-remove" name="id-to-remove" value="<%= idEntry%>">
                    </form>
                </div>

                <% } %>
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
                <button type="reset" value="Reset">Reset</button>
            </div>
        </form>
    </div>
    <div>

    </div>
</div>
</p>


</body>
</html>
