/**
 * FileName: FeeController.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;

import InteractionControllers.IO;

public class FeeController {
    /* Create a database instance */
    private static DatabaseController database = DatabaseController.getInstance();

    /* Get variables from database */
    private static int fee = database.getFee();
    private static int period = database.getPeriod();

    /* Getters */
    public static int getFee() { return fee; }
    public static int getPeriod() { return period; }

    /* Setters */
    public static void setFee()
    {
        int newFee = IO.getIntInput("Current Fees: $" + fee + "\nEnter New Fee:");
        fee = newFee;
        database.updateFee(newFee);
    }

    public static void setPeriod()
    {
        int newPeriod = IO.getIntInput("Current Posting Period: " + period + " days\nEnter New Posting Period:");
        period = newPeriod;
        database.updatePeriod(newPeriod);
    }

    /* Updates the balence based on set fees
    * @returns Filter initialized by FilterBuilder object
    */
    public static void charge()
    {
        database.updateBalance(fee);
    }
}
