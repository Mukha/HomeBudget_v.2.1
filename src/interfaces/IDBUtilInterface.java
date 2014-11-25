package interfaces;

import java.util.ArrayList;

/**
 * The <i>IDBUtilInterface</i> is a tool used to work
 * with database.
 * It implements the CRUD functions. Which stands for
 * Create, Read, Update, Delete. These functions correspond to
 * the insertion, selection, update and deletion queries.
 *
 * @author  Madina
 * @version 1.0
 * @see utils.DBUtilUser
 * @see utils.DBUtilCategory
 * @see utils.DBUtilIncome
 * @see utils.DBUtilExpense
 */
public interface IDBUtilInterface {
    /**
     * The <i>url</i> is the constant that contains the url of the database.
     */
    public static final String url = "jdbc:mysql://localhost:3306/test";

    /**
     * The <i>username</i> is the constant that contains the username
     * which is used to get access to the database.
     */
    public static final String username = "root";

    /**
     * The <i>password</i> is the constant that contains the password
     * which is used to get access to the database.
     */
    public static final String password = "";

    /**
     * The <i>insert()</i> method inserts new record to the database.
     *
     * @param object is the instance of specific class which is one
     *               of the following entities: User, Category, Income, Expense.
     * @see entities.User
     * @see entities.Category
     * @see entities.Income
     * @see entities.Expense
     */
    public void insert(Object object);

    /**
     * The <i>update()</i> method updates the existing record in the database.
     *
     * @param object is the instance of specific class which is one
     *               of the following entities: User, Category, Income, Expense.
     * @see entities.User
     * @see entities.Category
     * @see entities.Income
     * @see entities.Expense
     */
    public void update(Object object);

    /**
     * The <i>delete()</i> method removes the record from the database.
     *
     * @param object is the instance of specific class which is one
     *               of the following entities: User, Category, Income, Expense.
     * @see entities.User
     * @see entities.Category
     * @see entities.Income
     * @see entities.Expense
     */
    public void delete(Object object);

    /**
     * The <i>findAll()</i> method retrieves all records from the database
     * of specific class that can be one of the
     * following entities: User, Category, Income, Expense.
     *
     * Returns the array list of all records from database.
     * @return the array list of all records existing in the database.
     *
     * @see entities.User
     * @see entities.Category
     * @see entities.Income
     * @see entities.Expense
     */
    public ArrayList<Object> findAll();

    /**
     * The <i>findById()</i> method find the record by the
     * given id.
     *
     * @param id is the unique key of the record in the database.
     * Returns the object that owns the given id.
     * @return the object that owns the given id.
     *
     * @see entities.User
     * @see entities.Category
     * @see entities.Income
     * @see entities.Expense
     */
    public Object findById(int id);
}
