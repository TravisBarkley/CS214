import java.util.ArrayList;
import java.util.List;


public class Player {
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
        consume(item);
    }
    
    public void gainHunger()
    {
        hunger = hunger - 1;
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