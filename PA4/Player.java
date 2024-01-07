import java.util.ArrayList;
import java.util.List;


public class Player {
    private List<Item> inventory;
    private List<Item> eatInventory;
    private List<Item> drinkInventory; 
    private List<Item> consumeInventory;
    private boolean isWearing; 
    private Item wearItem;
    private boolean isInHand;
    private boolean isEquipped;
    private Item equippedItem;  
    private Item handItem;
    private int hydration; 
    private int hunger; 
    private double money; 

    public Player(){
        money = 0;
        hunger = 10; 
        hydration = 10; 
        inventory = new ArrayList<>();
        drinkInventory = new ArrayList<>();
        eatInventory = new ArrayList<>();
        consumeInventory = new ArrayList<>();
    }

    public Player(double moneyAmt)
    {
        this.money = moneyAmt;
        hunger = 10; 
        hydration = 10; 
        inventory = new ArrayList<>();
        drinkInventory = new ArrayList<>();
        eatInventory = new ArrayList<>();
        consumeInventory = new ArrayList<>();
    }

    public void addMoney(double money)
    {
        this.money = this.money + money;
    }

    public boolean removeMoney(double money)
    {
        this.money = this.money - money;
        return true;
    }

    public void consume(Item item)
    {
        relinquish(item);
    }

    public void drink(Item item)
    {
        hydration = hydration + 1; 
        consume(item);
    }
    
    public void loseHydration()
    {
        hydration = hydration - 1;
    }

    public void eat(Item item)
    {
        hunger = hunger + 2; 
        eatInventory.add(item);
        consume(item);
    }
    
    public void gainHunger()
    {
        hunger = hunger - 1;
    }

    public void equip(Item item) // not sure what this is for but saw that a expose equip was needed
    {
        if (isEquipped)
        {
           System.out.println("Already wearing an item.");
            return; // cant equip if already equipped 
        }
        isEquipped = true; 
        equippedItem = item; 
        relinquish(item);
    }
     
    public void dequip()
    {
        isEquipped = false; 
        acquire(equippedItem);
        equippedItem = null; 
    }


    public void wear(Item item)
    {
        if (isWearing){
            System.out.println("Already wearing an item.");
            return; // cant equip if already wearing 
        } 
        isWearing = true; 
        wearItem = item;
        relinquish(item);
    }
    public void takeOff()
    {
        isWearing=false;
        acquire(wearItem);
        wearItem= null; 
    }

    public void hold(Item item)
    {
        if (isInHand) {
            System.out.println("Already holding an item.");
            return; // cant equip if already in hand
        }
        isInHand = true; 
        handItem = item;
        relinquish(item);
    }

    public void dequipHand()
    {
        isInHand=false;
        acquire(handItem);
        handItem = null; 
    }

    public void addItem(Item item) {
        acquire(item);
    }

    public void acquire(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        relinquish(item);
    }

    public void relinquish(Item item) {
        inventory.remove(item);
    }

    public void displayInventory() {
        System.out.println("Player Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
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

    public List<Item> exposeInventory()
    {
        displayInventory();
        return inventory;
    }

    public List<Item> exposeWearInventory()
    {
        System.out.println("You are wearing " + wearItem.getName() + ".");
        List<Item> wearInv = new ArrayList<>();
        wearInv.add(wearItem);
        return wearInv;
    }

    public List<Item> exposeHoldInventory()
    {
        System.out.println("You are holding " + handItem.getName() + ".");
        List<Item> holdInv = new ArrayList<>();
        holdInv.add(handItem);
        return holdInv;
    }

    public List<Item> exposeEatInventory()
    {
        System.out.println("Items eaten:");
        for (Item item : eatInventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        return eatInventory;
    }

    public List<Item> exposeDrinkInventory()
    {
        System.out.println("Items drank:");
        for (Item item : drinkInventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        return drinkInventory;
    }

    public List<Item> exposeConsumeInventory()
    {
        System.out.println("All Items Consumed:");
        for (Item item : consumeInventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        return consumeInventory;
    }

    public List<Item> exposeEquipInventory() 
    {
        System.out.println("You have " + equippedItem.getName() + " equipped.");
        List<Item> equipInv = new ArrayList<>();
        equipInv.add(equippedItem);
        return equipInv;
    }

    public void exposeCommonMethodConsume()
    {
        Item potion = new Item("Health Potion", 5.0);
        consume(potion);
        consumeInventory.remove(potion); 
    }

    public void exposeCommonMethodEquip()
    {
        Item hat = new Item("Hat", 1);
        wear(hat);
    }

    public void exposeCommonMethodUse()
    {
        Item sword = new Item("Sword", 10.0);
        hold(sword);
    }

}    