package SystemControllers;

import java.util.*;


public class FeeController {
    private static DatabaseController database = DatabaseController.getInstance();
    private static int fee = database.getFee();
    private static int period = database.getPeriod();

    public static int getFee()
    {
        return fee;
    }

    public static int getPeriod()
    {
        return period;
    }

    public static void setFee(int fee)
    {
        this.fee = fee;
        database.updatefee(this.fee);
    }

    public static void updatePeriod(int period)
    {
        this.period = period;
        database.updatePeriod(this.period);
    }

    public static void charge()
    {
        database.increaseBalance();
    }
}