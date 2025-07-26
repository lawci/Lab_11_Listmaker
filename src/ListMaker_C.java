import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker_C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean done = false;
        String cmd = "";

        while (!done) {
            displayList(list);
            displayMenu();
            cmd = SafeInput.getRegExString(in, "Enter command", "[AaDdIiPpQq]").toUpperCase();

            switch (cmd) {
                case "A":
                    addItem(list, in);
                    break;
                case "D":
                    deleteItem(list, in);
                    break;
                case "I":
                    insertItem(list, in);
                    break;
                case "P":
                    displayList(list);
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
                        done = true;
                    }
                    break;
            }
        }

        System.out.println("Thanks for using ListMaker!");
    }

    private static void displayMenu() {
        System.out.println("\nMENU OPTIONS");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("I – Insert an item into the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit");
    }

    private static void displayList(ArrayList<String> list) {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("[The list is currently empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d: %s%n", i + 1, list.get(i));
            }
        }
    }

    private static void addItem(ArrayList<String> list, Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to add");
        list.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem(ArrayList<String> list, Scanner in) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList(list);
        int index = SafeInput.getRangedInt(in, "Enter item number to delete", 1, list.size()) - 1;
        String removed = list.remove(index);
        System.out.println("Removed item: " + removed);
    }

    private static void insertItem(ArrayList<String> list, Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert");
        int position;

        if (list.isEmpty()) {
            position = 0;
        } else {
            position = SafeInput.getRangedInt(in, "Enter position to insert (1 to " + (list.size() + 1) + ")", 1, list.size() + 1) - 1;
        }

        list.add(position, item);
        System.out.println("Item inserted.");
    }
}