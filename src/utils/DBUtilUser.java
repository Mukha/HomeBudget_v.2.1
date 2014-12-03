package utils;

import entities.User;
import interfaces.IDBUtilInterface;

import java.sql.*;
import java.util.ArrayList;

/**
 * The <i>DBUtilUser</i> class is used to work
 * with <b>User</b> table located in the database.
 * It implements the CRUD functions. Which stands for
 * Create, Read, Update, Delete. These functions correspond to
 * the insertion, selection, update and deletion queries.
 *
 * @author Madina
 * @version 1.0
 * @see utils.DBUtilUser
 */
public class DBUtilUser implements IDBUtilInterface {

    /**
     * The <i>equals()</i> checks for existence of record in the
     * User table. This is used to validate users.
     *
     * @param email        the email address of the application's user
     *                     used as username.
     * @param passwordUser the password of the user.
     * @return true if the user with given email and password exist
     * and false otherwise.
     * @throws java.sql.SQLException
     * @see entities.User
     */
    public boolean equals(String email, String passwordUser) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<User> array = new ArrayList<User>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM users";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new User(result.getInt("user_id"),
                            result.getString("fname"),
                            result.getString("lname"),
                            result.getString("email"),
                            result.getString("password")));
                }

                for (User u : array) {
                    if (u.getEmail().equals(email) &
                            u.getPassword().equals(passwordUser))
                        return true;
                }

                statement.close();
                result.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * The <i>insert()</i> method inserts new record to the User
     * table.
     *
     * @param object is the instance of class User.
     * @see entities.User
     */
    @Override
    public void insert(Object object) {
        User user = (User) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "INSERT INTO users(fname, lname, email, password) " +
                        "VALUES('" + user.getFname() + "', '" + user.getLname() +
                        "', '" + user.getEmail() + "', '" + user.getPassword() + "')";

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);

                statement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>update()</i> method updates the existing record in the database.
     *
     * @param object is the instance of class User.
     * @see entities.User
     */
    @Override
    public void update(Object object) {
        User user = (User) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "UPDATE users SET " +
                        "fname='" + user.getFname() + "', lname='" + user.getLname() +
                        "', email='" + user.getEmail() + "', password='" + user.getPassword() +
                        "' WHERE user_id=" + user.getUserId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);

                statement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>delete()</i> method removes the record from the database.
     *
     * @param object is the instance of class User.
     * @see entities.User
     */
    @Override
    public void delete(Object object) {
        User user = (User) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "DELETE FROM users WHERE users.user_id=" + user.getUserId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);

                statement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class User.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.User
     */
    @Override
    public ArrayList<Object> findAll() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<User> array = new ArrayList<User>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM users";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new User(result.getInt("user_id"),
                            result.getString("fname"),
                            result.getString("lname"),
                            result.getString("email"),
                            result.getString("password")));
                }

                statement.close();
                result.close();
                conn.close();
                return (ArrayList) array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The <i>findById()</i> method find the record by the
     * given id.
     *
     * @param id is the unique key of the record in the database.
     *           Returns the object that owns the given id which is an instance
     *           of class User.
     * @return the object that owns the given id which is an instance
     * of class User.
     * @see entities.User
     */
    @Override
    public Object findById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT * FROM users WHERE user_id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                if (result.next()) {
                    return new User(result.getInt("user_id"),
                            result.getString("fname"),
                            result.getString("lname"),
                            result.getString("email"),
                            result.getString("password"));
                }

                statement.close();
                result.close();
                conn.close();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The <i>getLastID()</i> method retrieves the id of the
     * last inserted record.
     *
     * @return the id of the last User item in the database.
     * @see entities.User
     */
    public int getLastID() {
        int lastId = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT last_insert_id() as last_id from users";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                lastId = result.getInt("last_id");

                statement.close();
                result.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lastId;
    }
}
