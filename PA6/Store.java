import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class Store {
    private List<Item> inventory;
    private List<Player> players_in_store; 
    private double money;
    static Logger logger = LogManager.getLogger(Store.class);


    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
        money = 1000;
    }

    public void enter(Player player){
        if (check_player_in_store(player) == false){
            players_in_store.add(player);
        } else {
            System.out.println("Player is already in the store.");
        }
    }

    public void exit(Player player){
         if (check_player_in_store(player) == true){
            players_in_store.remove(player);
        } else {
            System.out.println("Player never entered the store.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    private boolean check_player_in_store(Player player){
        int index =  players_in_store.indexOf(player);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Item getItemByName(String name) {
        // Iterate through the player's items and return the item with the matching name
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found in the player's inventory
    }

    public void buyItem(Item item, Player player) {
        Escrow.escrowItem(item);
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            //return false;
        }
        
        if (inventory.contains(item)) {
            if (player.removeMoney(item.getPrice())) {
                inventory.remove(item);
                player.addItem(item);
                //return true;
            }
        } else {
            System.out.println("Item not available in the store.");
        }
        //return false;
        logger.info("item bought!");
    }

    public void sellItem(Item item, Player player) {
         if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            //return false;
        }
        player.removeItem(item);
        player.addMoney(item.getPrice());
        inventory.add(item);
        //return true;
        logger.info("item sold!");
    }
    public void escrowFinalize()
    {
        Escrow.finalizeEscrow();
    }

    public void customerEscrowBuy()
    {
        Item item = new Item(null, money);
        Item tempItem = getItemByName(item.getName());
        Escrow.requestItem(tempItem);
        Escrow.escrowMoney(tempItem.getPrice());
        money = money + Escrow.returnMoney();
        inventory.remove(Escrow.receiveItem());
        logger.info("item bought!");
    }

    public void customerEscrowSell()
    {
        Item item = new Item(null, money);
        Escrow.escrowMoney(item.getPrice());
        Escrow.requestItem(item);
        money = money - Escrow.returnMoney();
        inventory.add(Escrow.receiveItem());
        logger.info("item sold!");
    }

    private void escrowBuy()
    {
        Item item = new Item(null, money);
        Escrow.escrowMoney(item.getPrice());
        Escrow.requestItem(item);
        money = money - Escrow.returnMoney();
        inventory.add(Escrow.receiveItem());
        logger.info("item bought!");
    }

    private void escrowSell()
    {
        Item item = new Item(null, money);
        Item tempItem = getItemByName(item.getName());
        Escrow.requestItem(tempItem);
        Escrow.escrowMoney(tempItem.getPrice());
        money = money + Escrow.returnMoney();
        inventory.remove(Escrow.receiveItem());
        logger.info("item sold!");
    }
}
