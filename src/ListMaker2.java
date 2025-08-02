import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker2 {
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("A - Add an item to the list");
            System.out.println("D - Delete an item from the list");
            System.out.println("I - Insert an item into the list");
            System.out.println("P - Print (display) the list");
            System.out.println("Q - Quit the program");

            String choice = SafeInput.getRegExString(scanner, "Enter your choice", "[AaDdIiPpQq]");

            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    displayList();
                    break;
                case "Q":
                    running = !confirmQuit();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void displayList() {
        if (list.isEmpty()) {
            System.out.println("\nThe list is currently empty");
        } else {
            System.out.println("\nCurrent List:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, list.get(i));
            }
        }
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to add");
        list.add(item);
        System.out.printf("'%s' added to the list%n", item);
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty - nothing to delete");
            return;
        }

        displayList(); // Show list before deletion
        int itemNum = SafeInput.getRangedInt(scanner, "Enter the item number to delete", 1, list.size());
        String removedItem = list.remove(itemNum - 1);
        System.out.printf("'%s' removed from the list%n", removedItem);
    }

    private static void insertItem() {
        if (list.isEmpty()) {
            System.out.println("List is empty - adding item at position 1");
            addItem();
            return;
        }

        displayList(); // Show list before insertion
        int position = SafeInput.getRangedInt(scanner, "Enter the position to insert at", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to insert");
        list.add(position - 1, item);
        System.out.printf("'%s' inserted at position %d%n", item, position);
    }

    private static boolean confirmQuit() {
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?");
    }
}