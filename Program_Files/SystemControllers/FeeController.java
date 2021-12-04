package SystemControllers;

import java.util.*;
import java.time.*;


public class FeeController {
    private static DatabaseController database = DatabaseController.getInstance();
    private static int fee = database.getFee();
    private static Period period = database.getPeriod();

    public static int getFee()
    {
        return fee;
    }

    public static void setFee(int fee)
    {
        this.fee = fee;
        database.updatefee(this.fee);
    }

    public static Period getPeriod()
    {
        return period;
    }

    public static void setPeriod(LocalDate periodStart, LocalDate periodEnd)
    {
        this.period[0] = periodStart;
        this.period[1] = periodEnd;

        try{
          this.period = Period.between(periodStart, PeriodEnd);
        }
        // catch(){
        //   // Invalid Period Exception
        // };


        database.updatePeriod(this.period);
    }

    public static void charge()
    {
        database.increaseBalance();
    }
}
