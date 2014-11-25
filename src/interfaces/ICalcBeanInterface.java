package interfaces;

import javax.ejb.Remote;

/**
 * The <i>ICalcBeanInterface</i> interface is used to
 * implement statistics and forecasting methods.
 *
 * @author  Madina
 * @version 1.0
 * @see ejbs.CalcBean
 */
@Remote
public interface ICalcBeanInterface {

    /**
     * The <i>statIncomesMonth()</i> method is used to
     * calculate the incomes statistics by the current month.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double statIncomesMonth();

    /**
     * The <i>statIncomesYear()</i> method is used to
     * calculate the incomes statistics by the current month.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double statIncomesYear();

    /**
     * The <i>statExpensesMonth()</i> method is used to
     * calculate the expense statistics by the current year.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double statExpensesMonth();

    /**
     * The <i>statExpensesYear()</i> method is used to
     * calculate the incomes statistics by the current year.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double statExpensesYear();

    /**
     * The <i>calcLose()</i> method is used to
     * calculate the expenses over incomes.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double calcLose();

    /**
     * The <i>calcEconomy()</i> method is used to
     * calculate the incomes over the expenses.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double calcEconomy();

    /**
     * The <i>forecastFutureExpense()</i> method is used to
     * calculate the amount of future possible expenses.
     *
     * Returns the result of the calculations.
     * @return the result of the calculations.
     */
    public double forecastFutureExpense();
}
