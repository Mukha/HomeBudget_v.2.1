package entities;

/**
 * The <i>Income</i> class is used to store the user's incomes.
 *
 * @author Madina
 * @version 1.0
 */
public class Income {
    /**
     * The field to store the income's id which is primary key.
     */
    private int id;

    /**
     * The field to store the income's category_id which is foreign key
     * that references to Category table.
     */
    private int categoryId;

    /**
     * The field to store the income's user_id which is foreign key
     * that references to User table.
     */
    private int userId;

    /**
     * The field to store the income's date.
     */
    private String date;

    /**
     * The field to store the income's description.
     */
    private String description;

    /**
     * The field to store the Expense's size.
     */
    private double size;

    /**
     * Default constructor
     */
    public Income () {}

    /**
     * The 6-parameter constructor. Is used to create an income instance.
     *
     * @param id the income's id.
     * @param categoryId category's id.
     * @param useId the user's id.
     * @param date the income's date.
     * @param description the income's description.
     * @param size the income's size.
     */
    public Income(int id, int categoryId, int useId, String date, String description, double size) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = useId;
        this.date = date;
        this.description = description;
        this.size = size;
    }

    /**
     * The <i>getId()</i> is used to retrieve the income's id.
     * Returns the income's id.
     * @return the income's id.
     */
    public int getId() {
        return id;
    }

    /**
     * The <i>setId()</i> is used to set the income's id.
     *
     * @param id to set the income's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The <i>getCategoryId()</i> is used to retrieve the category's id.
     * Returns the category's id.
     * @return the category's id.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * The <i>setCategoryId()</i> is used to set the category's id.
     *
     * @param categoryId to set the category's id.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * The <i>getUserId()</i> is used to retrieve the user's id.
     * Returns the user's id.
     * @return the user's id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * The <i>setUserId()</i> is used to set the user's id.
     *
     * @param useId to set the user's id.
     */
    public void setUserId(int useId) {
        this.userId = useId;
    }

    /**
     * The <i>getDate()</i> is used to retrieve the income's date.
     * Returns the income's date.
     * @return the income's date.
     */
    public String getDate() {
        return date;
    }

    /**
     * The <i>setDate()</i> is used to set the income's date.
     *
     * @param date to set the income's date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The <i>getDescription()</i> is used to retrieve the income's description.
     * Returns the income's description.
     * @return the income's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The <i>setDescription()</i> is used to set the income's description.
     *
     * @param description to set the income's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The <i>getSize()</i> is used to retrieve the Expense's size.
     * Returns the Expense's size.
     * @return the Expense's size.
     */
    public double getSize() {
        return size;
    }

    /**
     * The <i>setSize()</i> is used to set the Expense's size.
     *
     * @param size to set the Expense's size.
     */
    public void setSize(double size) {
        this.size = size;
    }
}
