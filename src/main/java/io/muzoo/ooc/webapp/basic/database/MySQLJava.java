package io.muzoo.ooc.webapp.basic.database;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.Optional;

public class MySQLJava {

    public static final String database_Hostname = Optional.ofNullable(System.getenv("DATABASE_HOSTNAME")).orElse("localhost");
    public static final String database_Port = Optional.ofNullable(System.getenv("DATABASE_PORT")).orElse("3306");

    public static final String jdbcDriverStr = "com.mysql.jdbc.Driver";
    public static final String jdbcURL = String.format("jdbc:mysql://%s:%s/webappdb?useSSL=false&characterEncoding=UTF-8&user=root&password=123456", database_Hostname, database_Port);


    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private static final MySQLJava mySQLJava = new MySQLJava();

    public static MySQLJava getInstance() {
        return mySQLJava;
    }

    public MySQLJava() {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isUser(String username){
        try {
            preparedStatement = connection.prepareStatement("select * from webappdb.userstable where username=?;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String pwd){
        try {
            preparedStatement = connection.prepareStatement("select password from webappdb.userstable where username=?;");
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                String passwordHash = resultSet.getString("password");
                return BCrypt.checkpw(pwd, passwordHash);
            } else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getId(String username){
        try {
            preparedStatement = connection.prepareStatement("select id FROM webappdb.userstable where username=?;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String getUsername(int id){
        try {
            preparedStatement = connection.prepareStatement("select username from webappdb.userstable where id=?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("username");
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String getName(int userID){
        try {
            preparedStatement = connection.prepareStatement("select name from webappdb.userstable where id=?;");
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getName(String username){
        try {
            preparedStatement = connection.prepareStatement("select name from webappdb.userstable where username=?;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean addNewUser(String username, String pwd, String name){
        try {
            preparedStatement = connection.prepareStatement("insert into webappdb.userstable values (DEFAULT,?,?,?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwd);
            preparedStatement.setString(3, name);
            preparedStatement.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getUsersData(){
        try{
            return statement.executeQuery("select id, username, name from webappdb.userstable;");
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUser(int id){
        try {
            preparedStatement = connection.prepareStatement("delete from webappdb.userstable where id=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("ALTER TABLE webappdb.userstable AUTO_INCREMENT = 1");
            preparedStatement.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean editUser(int id, String username, String password, String name){
        try {
            preparedStatement = connection.prepareStatement("update webappdb.userstable set username=?, password=?, name=? where id=?;");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
