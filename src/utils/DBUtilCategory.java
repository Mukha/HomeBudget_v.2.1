package utils;

import entities.Category;
import interfaces.IDBUtilInterface;

import java.sql.*;
import java.util.ArrayList;

/**
 * The <i>DBUtilCategory</i> class is used to work
 * with <b>Category</b> table located in the database.
 * It implements the CRUD functions. Which stands for
 * Create, Read, Update, Delete. These functions correspond to
 * the insertion, selection, update and deletion queries.
 *
 * @author Madina
 * @version 1.0
 * @see utils.DBUtilCategory
 */
public class DBUtilCategory implements IDBUtilInterface {
    /**
     * The <i>insert()</i> method inserts new record to the Category
     * table.
     *
     * @param object is the instance of class Category.
     * @see entities.Category
     */
    @Override
    public void insert(Object object) {
        Category category = (Category) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "INSERT INTO category(category_name) " +
                        "VALUES('" + category.getCategoryName() + "')";

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The <i>update()</i> method updates the existing record in the database.
     *
     * @param object is the instance of class Category.
     * @see entities.Category
     */
    @Override
    public void update(Object object) {
        Category category = (Category) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "UPDATE category SET " +
                        "category_name='" + category.getCategoryName()
                        + "' WHERE category_id=" + category.getCategoryId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The <i>delete()</i> method removes the record from the database.
     *
     * @param object is the instance of class Category.
     * @see entities.Category
     */
    @Override
    public void delete(Object object) {
        Category category = (Category) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "DELETE FROM category WHERE category.category_id="
                        + category.getCategoryId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class Category.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.Category
     */
    @Override
    public ArrayList<Object> findAll() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Category> array = new ArrayList<Category>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM category";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Category(result.getInt("category_id"),
                            result.getString("category_name")));
                }
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
     *           of class Category.
     * @return the object that owns the given id which is an instance
     * of class Category.
     * @see entities.Category
     */
    @Override
    public Object findById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT * FROM category WHERE category_id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                if (result.next()) {
                    return new Category(result.getInt("category_id"),
                            result.getString("category_name"));
                }
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
     * @return the id of the last Category item in the database.
     * @see entities.Category
     */
    public int getLastID() {
        int lastId = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT last_insert_id() as last_id from category";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                lastId = result.getInt("last_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lastId;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class Category.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.Category
     */

    public ArrayList<Object> findAllForIncomes() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Category> array = new ArrayList<Category>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM category where type=0";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Category(result.getInt("category_id"),
                            result.getString("category_name")));
                }
                return (ArrayList) array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class Category.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.Category
     */

    public ArrayList<Object> findAllForExpenses() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Category> array = new ArrayList<Category>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM category where type=1";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Category(result.getInt("category_id"),
                            result.getString("category_name")));
                }
                return (ArrayList) array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
