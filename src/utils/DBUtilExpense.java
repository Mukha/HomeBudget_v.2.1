package utils;

import entities.Expense;
import interfaces.IDBUtilInterface;

import java.sql.*;
import java.util.ArrayList;

/**
 * The <i>DBUtilExpense</i> class is used to work
 * with <b>Expense</b> table located in the database.
 * It implements the CRUD functions. Which stands for
 * Create, Read, Update, Delete. These functions correspond to
 * the insertion, selection, update and deletion queries.
 *
 * @author Madina
 * @version 1.0
 * @see utils.DBUtilExpense
 */
public class DBUtilExpense implements IDBUtilInterface {
    /**
     * The <i>insert()</i> method inserts new record to the
     * Expense table.
     *
     * @param object is the instance of class Expense.
     * @see entities.Expense
     */
    @Override
    public void insert(Object object) {
        Expense exp = (Expense) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "INSERT INTO expense(category_id, user_id, dateQ, description, sizeQ) " +
                        "VALUES('" + exp.getCategoryId() + "', '" + exp.getUserId() +
                        "', '" + exp.getDate() + "', '" + exp.getDescription() + "', '" + exp.getSize() + "')";

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>update()</i> method updates the existing record in the database.
     *
     * @param object is the instance of class Expense.
     * @see entities.Expense
     */
    @Override
    public void update(Object object) {
        Expense exp = (Expense) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "UPDATE expense SET " +
                        "category_id='" + exp.getCategoryId() + "', user_id='" + exp.getUserId() +
                        "', dateQ='" + exp.getDate() + "', description='" + exp.getDescription() +
                        "', sizeQ='" + exp.getSize() + "' WHERE id=" + exp.getId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>delete()</i> method removes the record from the database.
     *
     * @param object is the instance of class Expense.
     * @see entities.Expense
     */
    @Override
    public void delete(Object object) {
        Expense exp = (Expense) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "DELETE FROM expense WHERE expense.id=" + exp.getId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class Expense.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.Expense
     */
    @Override
    public ArrayList<Object> findAll() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Expense> array = new ArrayList<Expense>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM expense";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Expense(result.getInt("id"),
                            result.getInt("category_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ")));
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
     *           of class Expense.
     * @return the object that owns the given id which is an instance
     * of class Expense.
     * @see entities.Expense
     */
    @Override
    public Object findById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT * FROM expense WHERE id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                if (result.next()) {
                    return new Expense(result.getInt("id"),
                            result.getInt("category_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ"));
                }
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
