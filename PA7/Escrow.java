import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class Escrow {
    public static double escrowMoney = 0.0;
    public static List<Item> escrowItems = new ArrayList<>();
    static Logger logger = LogManager.getLogger(Escrow.class);
    
    public static void escrowMoney(double money)
    {
        escrowMoney = escrowMoney + money;
        logger.info("money escrowed!");
    }
    public static double receiveMoney()
    {
        double moneyToRec = escrowMoney; 
        escrowMoney = 0.0; 
        return moneyToRec;
    }
    public static double returnMoney()
    {
        return escrowMoney;
    }
    public static void escrowItem(Item item)
    {
        escrowItems.add(item);
        logger.info("item escrowed!");
    }
    public static Item receiveItem() //throws EscrowException 
    {
        if (escrowItems.isEmpty())
            return null;
        return escrowItems.remove(0);
    }
    public static void requestItem(Item item)
    {
        escrowItem(item);
    }

    public static void finalizeEscrow()
    {
        escrowMoney = 0.0;
        escrowItems.clear();
        logger.info("escrowed finalized!");
    }
}
