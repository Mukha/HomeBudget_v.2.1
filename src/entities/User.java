package entities;

/**
 * The <i>User</i> class is used to store the users of the
 * application.
 *
 * @author Madina
 * @version 1.0
 */
public class User {

    /**
     * The field to store the user's id which is primary key.
     */
    private int userId;

    /**
     * The field to store the user's first name.
     */
    private String fname;

    /**
     * The field to store the user's last name.
     */
    private String lname;

    /**
     * The field to store the user's email.
     */
    private String email;

    /**
     * The field to store the user's password.
     */
    private String password;

    /**
     * The default constructor.
     */
    public User() {}

    /**
     * The 5-parameter constructor. Is used to create a user instance.
     *
     * @param user_id the user's id.
     * @param fname user's first name.
     * @param lname the last name of the user.
     * @param email the email address of the user.
     * @param password the password of the user.
     */
    public User(int user_id, String fname, String lname, String email, String password) {
        this.userId = user_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
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
     * @param userId to set the user's id.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * The <i>getFname()</i> is used to retrieve the user's first name.
     * Returns the user's first name.
     * @return the user's first name.
     */
    public String getFname() {
        return fname;
    }

    /**
     * The <i>setFname()</i> is used to set the user's first name.
     *
     * @param fname to set the user's first name.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * The <i>getLname()</i> is used to retrieve the user's last name.
     * Returns the user's last name.
     * @return the user's last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * The <i>setLname()</i> is used to set the user's last name.
     *
     * @param lname to set the user's last name.
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * The <i>getEmail()</i> is used to retrieve the user's email.
     * Returns the user's email.
     * @return the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The <i>setEmail()</i> is used to set the user's email.
     *
     * @param email to set the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The <i>getPassword()</i> is used to retrieve the user's password.
     * Returns the user's password.
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The <i>setPassword()</i> is used to set the user's password.
     *
     * @param password to set the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
