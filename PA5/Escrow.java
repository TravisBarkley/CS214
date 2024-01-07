import java.util.ArrayList;
import java.util.List;
public class Escrow {
    public static double escrowMoney = 0.0;
    public static List<Item> escrowItems = new ArrayList<>();
    
    public static void escrowMoney(double money)
    {
        escrowMoney = escrowMoney + money;
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
    }
    public static Item receiveItem() //throws EscrowException 
    {
        //if (escrowItems.isEmpty())
            //throw new EscrowException("There is no item in escrow");
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
    }
}
