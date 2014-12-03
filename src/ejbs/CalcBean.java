package ejbs;

import entities.Expense;
import entities.Income;
import interfaces.ICalcBeanInterface;
import utils.DBUtilIncome;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * The <i>CalcBeanBean</i> class is used to
 * implement statistics and forecasting methods.
 *
 * @author  Madina
 * @version 1.0
 * @see interfaces.ICalcBeanInterface
 */
@Stateless(name = "CalcBeanEJB")
public class CalcBean implements ICalcBeanInterface{

    /**
     * Default Constructor.
     */
    public CalcBean() { }


    /**
     * The <i>statIncomesMonth()</i> method is used to
     * calculate the incomes statistics by the current month.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double statIncomesMonth() {



        return 0;
    }

    /**
     * The <i>statIncomesYear()</i> method is used to
     * calculate the incomes statistics by the current month.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double statIncomesYear() {
        return 0;
    }

    /**
     * The <i>statExpensesMonth()</i> method is used to
     * calculate the expense statistics by the current year.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double statExpensesMonth() {
        return 0;
    }

    /**
     * The <i>statExpensesYear()</i> method is used to
     * calculate the incomes statistics by the current year.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double statExpensesYear() {
        return 0;
    }

    /**
     * The <i>calcLose()</i> method is used to
     * calculate the expenses over incomes.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double calcLose() {
        return 0;
    }

    /**
     * The <i>calcEconomy()</i> method is used to
     * calculate the incomes over the expenses.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double calcEconomy() {
        return 0;
    }

    /**
     * The <i>forecastFutureExpense()</i> method is used to
     * calculate the amount of future possible expenses.
     *
     * Returns the result of the calculations.
     *
     * @return the result of the calculations.
     */
    @Override
    public double forecastFutureExpense() {
        return 0;
    }


}
