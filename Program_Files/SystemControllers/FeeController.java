package SystemControllers;

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

    public static void setFee(int newFee)
    {
        fee = newFee;
        database.updateFee(newFee);
    }

    public static void updatePeriod(int newPeriod)
    {
        period = newPeriod;
        database.updatePeriod(newPeriod);
    }

    public static void charge()
    {
        database.updateBalance(fee);
    }
}