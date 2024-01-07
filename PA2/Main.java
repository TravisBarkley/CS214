import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Player {
    private List<Item> inventory;
    private boolean isOnHead; 
    private String headName;
    private boolean isOnChest;
    private String chestName;
    private boolean isOnLegs;
    private String legsName;
    private boolean isInHand; 
    private String handName;
    private int hydration; 
    private int hunger; 
    private double money; 

    public Player(){
        money = 0;
        hunger = 10; 
        hydration = 10; 
        inventory = new ArrayList<>();
    }

    public Player(double moneyAmt)
    {
        this.money = moneyAmt;
        hunger = 10; 
        hydration = 10; 
        inventory = new ArrayList<>();
    }

    public void addMoney(double money)
    {
        this.money += money;
    }

    public boolean removeMoney(double money)
    {
        this.money -= money;
        return true;
    }

   public void consume(Item item)
    {
        relinquish(item);
    }

    public void drink(Item item)
    {
        hydration += 1; 
        consume(item);
    }
    
    public void loseHydration()
    {
        hydration -= 1;
    }

    public void eat(Item item)
    {
        hunger +=2; 
        consume(item);
    }

    public void gainHunger()
    {
        hunger -= 1;
    }

    public void wearHead(Item headItem)
    {
        if (isOnHead) return; // cant equip if already on head
        isOnHead = true; 
        headName = headItem.getName();
    }
    public void dequipHead()
    {
        isOnHead=false;
        headName = null; 
    }

    public void wearChest(Item chestItem)
    {
        if (isOnChest) return; // cant equip if already on chest
        isOnChest = true; 
        chestName = chestItem.getName();
    }

    public void dequipChest()
    {
        isOnChest=false;
        chestName = null; 
    }

    public void wearLegs(Item legsItem)
    {
        if (isOnLegs) return; // cant equip if already on legs
        isOnLegs = true; 
        legsName = legsItem.getName();
    }

    public void dequipLegs()
    {
        isOnLegs=false;
        legsName = null; 
    }

    public void hold(Item holdableItem)
    {
        if (isInHand) return; // cant equip if already in hand
        isInHand = true; 
        handName = holdableItem.getName();
    }

    public void dequipHand()
    {
        isInHand=false;
        handName = null; 
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void acquire(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void relinquish(Item item) {
        inventory.remove(item);
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
    
}                       


class Store {
    private List<Item> inventory;
    private List<Player> players_in_store; 

    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
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


    public boolean buyItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            return false;
        }
        
        if (inventory.contains(item)) {
            if (player.removeMoney(item.getPrice())) {
                inventory.remove(item);
                player.addItem(item);
                return true;
            }
        } else {
            System.out.println("Item not available in the store.");
        }
        return false;
    }

    public boolean sellItem(Item item, Player player) {
         if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            return false;
        }
        player.removeItem(item);
        player.addMoney(item.getPrice());
        inventory.add(item);
        return true;
    }
}


public class Main {
    public static void main(String[] args) {
        Item sword = new Item("Sword", 10.0);
        Item potion = new Item("Health Potion", 5.0);
        Item hat = new Item("Hat", 1);

        Store store = new Store();
        store.addItem(sword);
        store.addItem(potion);
        store.addItem(hat);

        Player player = new Player(100.0);

        store.displayInventory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 4 to exit):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.enter(player);
                storeMenu(scanner, store, player);
                store.exit(player);
            } else if (input.equals("4")) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }

        scanner.close();
    }

    public static void storeMenu(Scanner scanner, Store store, Player player) {
        while (true) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Buy an item");
            System.out.println("2. Sell an item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit store");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.displayInventory();
                System.out.println("Enter the name of the item you want to buy:");
                String itemName = scanner.nextLine();
                Item item = store.getItemByName(itemName);
                if (item != null) {
                    if (store.buyItem(item, player)){
                        System.out.println("Item purchased successfully!");
                    } else {
                        System.out.println("Could not purchase the desired item.");
                    }
                } else {
                    System.out.println("Item not available in the store.");
                }
            } else if (input.equals("2")) {
                System.out.println("Enter the name of the item you want to sell:");
                String itemName = scanner.nextLine();
                Item item = player.getItemByName(itemName);
                if (item != null) {
                    store.sellItem(item, player);
                    System.out.println("Item sold successfully!");
                } else {
                    System.out.println("Item not found in your inventory.");
                }
            } else if (input.equals("3")) {
                store.displayInventory();
            } else if (input.equals("4")) {
                System.out.println("Exiting the store...");
                store.exit(player);
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

}