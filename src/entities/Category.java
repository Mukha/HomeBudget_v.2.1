package entities;

/**
 * The <i>Category</i> class is used to store the categories of the
 * incomes and expenses.
 *
 * @author Madina
 * @version 1.0
 */
public class Category {
    /**
     * The field to store the category's id which is primary key.
     */
    private int categoryId;

    /**
     * The field to store the category's name.
     */
    private String categoryName;

    /**
     * The default constructor.
     */
    public Category() {}

    /**
     * The 2-parameter constructor. Is used to create a category instance.
     *
     * @param categoryId the category's id.
     * @param categoryName category's name.
     */
    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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
     * The <i>getCategoryName()</i> is used to retrieve the category's name.
     * Returns the category's name.
     * @return the category's name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * The <i>setCategoryName()</i> is used to set the category's name.
     *
     * @param categoryName to set the category's name.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
