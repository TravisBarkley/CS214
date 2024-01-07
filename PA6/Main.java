import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        mainLoop(scanner, store, player);

        scanner.close();
    }

    public static void exposeGameSetup()
    {
        main(null);
    }

    public static void exposeGamePlay(Scanner scanner, Store store, Player player)
    {
        mainLoop(scanner, store, player);
    }

    public static void exposeGameStop()
    {
        gameStop();
    }

    public static void gameStop()
    {
        System.out.println("You have chose to end the game.\n Thank You for playing.\n Please input 4 until the game is terminated.");
    }

    public static void mainLoop(Scanner scanner, Store store, Player player) {
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
                buyItem(scanner, store, player);
            } else if (input.equals("2")) {
                sellItem(scanner, store, player);
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

    public static void buyItem(Scanner scanner, Store store, Player player) {
        store.displayInventory();
        System.out.println("Enter the name of the item you want to buy:");
        String itemName = scanner.nextLine();
        Item item = store.getItemByName(itemName);
        if (item != null) {
            store.buyItem(item, player);
            System.out.println("Item purchased successfully!");
        } else {
            System.out.println("Item not available in the store.");
        }
    }

    public static void sellItem(Scanner scanner, Store store, Player player) {
        System.out.println("Enter the name of the item you want to sell:");
        String itemName = scanner.nextLine();
        Item item = player.getItemByName(itemName);
        if (item != null) {
            store.sellItem(item, player);
            System.out.println("Item sold successfully!");
        } else {
            System.out.println("Item not found in your inventory.");
        }
    }

}