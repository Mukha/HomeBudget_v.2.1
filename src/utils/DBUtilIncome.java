package utils;

import entities.Income;
import interfaces.IDBUtilInterface;

import java.sql.*;
import java.util.ArrayList;

/**
 * The <i>DBUtilIncome</i> class is used to work
 * with <b>Income</b> table located in the database.
 * It implements the CRUD functions. Which stands for
 * Create, Read, Update, Delete. These functions correspond to
 * the insertion, selection, update and deletion queries.
 *
 * @author Madina
 * @version 1.0
 * @see utils.DBUtilIncome
 */
public class DBUtilIncome implements IDBUtilInterface {
    /**
     * The <i>insert()</i> method inserts new record to the
     * Income table.
     *
     * @param object is the instance of class Income.
     * @see entities.Income
     */
    @Override
    public void insert(Object object) {
        Income in = (Income) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "INSERT INTO income(categort_id, user_id, dateQ, description, sizeQ) " +
                        "VALUES('" + in.getCategoryId() + "', '" + in.getUserId() +
                        "', '" + in.getDate() + "', '" + in.getDescription() + "', '" + in.getSize() + "')";

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
     * @param object is the instance of class Income.
     * @see entities.Income
     */
    @Override
    public void update(Object object) {
        Income in = (Income) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "UPDATE income SET " +
                        "categort_id='" + in.getCategoryId() + "', user_id='" + in.getUserId() +
                        "', dateQ='" + in.getDate() + "', description='" + in.getDescription() +
                        "', sizeQ='" + in.getSize() + "' WHERE id=" + in.getId();

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
     * @param object is the instance of class Income.
     * @see entities.Income
     */
    @Override
    public void delete(Object object) {
        Income in = (Income) object;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected

                String sql = "DELETE FROM income WHERE income.id=" + in.getId();

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * which are the instances of class Income.
     * <p/>
     * Returns the array list of all records from database.
     *
     * @return the array list of all records existing in the database.
     * @see entities.Income
     */
    @Override
    public ArrayList<Object> findAll() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Income> array = new ArrayList<Income>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM income";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Income(result.getInt("id"),
                            result.getInt("categort_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ")
                    ));
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
     *           of class Income.
     * @return the object that owns the given id which is an instance
     * of class Income.
     * @see entities.Income
     */
    @Override
    public Object findById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) { // Connected
                String sql = "SELECT * FROM income WHERE id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                if (result.next()) {
                    return new Income(result.getInt("id"),
                            result.getInt("categort_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ")
                            );
                }
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The <i>findAll()</i> method retrieves all incomes from the database
     * which are related to specific user.
     * <p/>
     *
     * @param id the id of the user.
     * Returns the array list of all incomes of specific user.
     * @return the array list of all incomes of specific user.
     * @see entities.Income
     */
    public ArrayList<Object> findIncomes(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Income> array = new ArrayList<Income>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM income WHERE user_id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Income(result.getInt("id"),
                            result.getInt("categort_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ")
                    ));
                }
                return (ArrayList) array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * The <i>findAll()</i> method retrieves all incomes from the database
     * which are related to specific category.
     * <p/>
     *
     * @param id the id of the category.
     * Returns the array list of all incomes of specific category.
     * @return the array list of all incomes of specific category.
     * @see entities.Income
     */
    public ArrayList<Object> findIncomesByCategory(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<Income> array = new ArrayList<Income>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM income WHERE categort_id=" + id;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new Income(result.getInt("id"),
                            result.getInt("categort_id"),
                            result.getInt("user_id"),
                            result.getString("dateQ"),
                            result.getString("description"),
                            result.getDouble("sizeQ")
                    ));
                }
                return (ArrayList) array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** EDIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * The <i>findAll()</i> method retrieves all incomes from the database
     * which are related to specific category.
     * <p/>
     *
     * Returns the array list of all incomes of specific category.
     * @return the array list of all incomes of specific category.
     * @see entities.Income
     */
    /*public ArrayList<Double> calculatePercentage(int userId){
        ArrayList<Double> percents = new ArrayList<Double>();
        DBUtilIncome dbi = new DBUtilIncome();
        double sum = 0;
        ArrayList<Income> allIncomes = (ArrayList) dbi.findIncomes(userId);
        for (Income in : allIncomes) {
            sum+=in.getSize();
        }
        for ();
        for (Income in : allIncomes) {
            ArrayList<Income> ins = (ArrayList) dbi.findIncomesByCategory();
        }
        return null;
    }*/
}
