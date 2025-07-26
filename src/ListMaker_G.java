import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker_G {

    // Class-level static variables to be accessible by all static methods
    static ArrayList<String> list = new ArrayList<>(); // The list to be managed [cite: 305, 323]
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        final String MENU_PROMPT = "Select an option: [A]dd, [D]elete, [I]nsert, [P]rint, [Q]uit";
        boolean needsSave = false; // A flag to track if changes have been made
        boolean running = true;

        do {
            // Display the current list before showing the menu
            displayList();

            // Get a valid menu choice from the user [cite: 334]
            String command = SafeInput.getRegExString(in, MENU_PROMPT, "[AaDdIiPpQq]");
            command = command.toUpperCase();

            // Execute the chosen command
            switch (command) {
                case "A":
                    addItem();
                    needsSave = true;
                    break;
                case "D":
                    deleteItem();
                    needsSave = true;
                    break;
                case "I":
                    insertItem();
                    needsSave = true;
                    break;
                case "P":
                    // The list is already printed at the top of the loop,
                    // but this option explicitly handles the 'P' command.
                    System.out.println("Displaying the list again:");
                    // displayList() is called at the start of the next loop iteration.
                    break;
                case "Q":
                    running = !quitProgram(); // quitProgram returns true if we should exit
                    break;
            }
        } while (running);
    }

    /**
     * Displays the current list with 1-based numbering for user convenience.
     */
    private static void displayList() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        if (list.isEmpty()) {
            System.out.println("The list is currently empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                // Display item number (i + 1) and the item itself
                System.out.printf("%3d. %s\n", i + 1, list.get(i));
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Prompts the user for an item and adds it to the end of the list.
     */
    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item); // Adds the item to the end of the list [cite: 308, 328]
        System.out.println("'" + item + "' was added to the list.");
    }

    /**
     * Prompts the user to select an item number to delete from the list.
     */
    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("Cannot delete from an empty list.");
            return;
        }
        // Get the item number to delete, ensuring it's within the valid range [cite: 337]
        int itemIndex = SafeInput.getRangedInt(in, "Enter the number of the item to delete", 1, list.size());
        String removedItem = list.remove(itemIndex - 1); // Convert 1-based to 0-based index
        System.out.println("'" + removedItem + "' was removed from the list.");
    }

    /**
     * Prompts the user for an item and an insertion point to add it to the list.
     */
    private static void insertItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Adding item as the first element.");
            addItem(); // If list is empty, inserting is the same as adding
            return;
        }
        // Get the position to insert the new item [cite: 328]
        int position = SafeInput.getRangedInt(in, "Enter the position to insert the item", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
        list.add(position - 1, item); // Convert 1-based position to 0-based index [cite: 312]
        System.out.println("'" + item + "' was inserted at position " + position + ".");
    }

    /**
     * Asks the user for confirmation before quitting the program.
     * @return true if the user confirms to quit, false otherwise.
     */
    private static boolean quitProgram() {
        // Use getYNConfirm to ask the user if they are sure they want to quit [cite: 328, 338]
        boolean confirmQuit = SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
        if(confirmQuit) {
            System.out.println("Exiting the ListMaker. Goodbye!");
        }
        return confirmQuit;
    }
}