import java.util.Scanner;

// -----------------------------------------------------
// Assignment (0)
// © Gaganpreet Singh
// Written by: Gaganpreet Singh | 40230095
// -----------------------------------------------------
public class Main {
    private static final String PASSWORD = "password";
    public static Scanner scan = new Scanner(System.in);
    public static Computer[] inventory;

    public static void main(String[] args) {

        System.out.println("Welcome, how are you?");
        int sizeOfInventory = 0;

        while (true) {
            System.out.print("Maximum number of computers his/her computer store can contain: ");
            try {
                sizeOfInventory = Integer.parseInt(scan.nextLine());
                inventory = new Computer[sizeOfInventory];
                break;
            } catch (Exception ex) {
                System.out.println("Enter positive digits only");
            }
        }

        ///This while loop is used to display main menu to users until user exits the system
        while (true) {
            try {
                displayMainMenu();
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        enterNewComputers();
                        break;
                    case 2:
                        updateComputer();
                        break;
                    case 3:
                        System.out.print("Enter brand name: ");
                        String brand = scan.nextLine();
                        findComputersBy(brand);
                        break;
                    case 4:
                        System.out.print("Enter price: ");
                        double price = Double.parseDouble(scan.nextLine());
                        findCheaperThan(price);
                        break;
                    case 5:
                        System.out.print("Thank you for using the system");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
    }

    public static void enterNewComputers() {
        boolean passwordCheck = passwordCheck();
        if (passwordCheck) {
            System.out.print("How many computers to add: ");
            int numberOfComputersToBeAdded = Integer.parseInt(scan.nextLine());

            if(numberOfComputersToBeAdded<=0){
                System.out.println("Number should be greater than 0");
                return;
            }
            int spaceAvailable = 0;

            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == null) {
                    spaceAvailable = inventory.length - i;
                    break;
                }
            }

            if (spaceAvailable < numberOfComputersToBeAdded) {
                System.out.println("You can only add " + (spaceAvailable) + " computers");
                numberOfComputersToBeAdded = spaceAvailable;
            }

            for (int i = 0; i < numberOfComputersToBeAdded; i++) {
                addComputerToInventory();
            }
        }
    }

    /**
     * update the data of computer
     */
    public static void updateComputer() {
        boolean passwordCheck = passwordCheck();

        if (passwordCheck) {
            // This while loop is to keep on updating the data of computer until user exits the option
            while (true) {
                System.out.print("Enter computer number to be updated: ");
                int computerNumber = Integer.parseInt(scan.nextLine()) - 1;

                if (computerNumber > inventory.length || inventory[computerNumber] == null) {
                    System.out.println("Invalid number. Do you want to enter another number or go to main menu");
                    System.out.println("Press 1 to enter new number");
                    System.out.println("Press 2 to view main menu");

                    int anotherChoice = Integer.parseInt(scan.nextLine());

                    if (anotherChoice == 1) {
                        continue;
                    } else if (anotherChoice == 2) {
                        break;
                    }
                }

                Computer computerToBeUpdated = inventory[computerNumber];
                displayComputer(computerNumber, computerToBeUpdated);

                while (true) {
                    System.out.println("What information would you like to change?");
                    System.out.println("1. brand");
                    System.out.println("2. model");
                    System.out.println("3. SN");
                    System.out.println("4. price");
                    System.out.println("5. Quit");
                    System.out.print("Enter your choice > ");
                    int c = Integer.parseInt(scan.nextLine());

                    if (c == 1) {
                        System.out.print("Enter new brand name: ");
                        String brand = scan.nextLine();

                        computerToBeUpdated.setBrand(brand);
                    } else if (c == 2) {
                        System.out.print("Enter new model name: ");
                        String model = scan.nextLine();

                        computerToBeUpdated.setModel(model);
                    } else if (c == 3) {
                        System.out.print("Enter new SN: ");
                        long sn = Long.parseLong(scan.nextLine());

                        computerToBeUpdated.setSN(sn);
                    } else if (c == 4) {
                        System.out.print("Enter new Price: $");
                        double price = Double.parseDouble(scan.nextLine());

                        computerToBeUpdated.setPrice(price);
                    } else if (c == 5) {
                        break;
                    }

                    inventory[computerNumber] = computerToBeUpdated;
                    displayComputer(computerNumber, computerToBeUpdated);
                }
                break;
            }
        }
    }

    /**
     * Display the main menu to user
     */
    public static void displayMainMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Enter new computers (password required)");
        System.out.println("2. Change information of a computer (password required)");
        System.out.println("3. Display all computers by a specific brand");
        System.out.println("4. Display all computers under a certain a price.");
        System.out.println("5. Quit");
        System.out.print("Please enter your choice > ");
    }

    /**
     * use to check whether the password entered by the user is correct or not
     * @return
     */
    public static boolean passwordCheck() {
        boolean passwordCheck = false;

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter your password: ");
            String password = scan.nextLine();

            if (password.equals(PASSWORD)) {
                passwordCheck = true;
                break;
            } else {
                System.out.println("Incorrect password Try remaining: " + (2 - i));
            }
        }

        return passwordCheck;
    }

    /**
     * Adds computer to the array of computer called inventory
     */
    public static void addComputerToInventory() {
        System.out.print("Brand: ");
        String brand = scan.nextLine();

        System.out.print("Model: ");
        String model = scan.nextLine();

        System.out.print("SN: ");
        long sn = Long.parseLong(scan.nextLine());

        System.out.print("Price: ");
        double price = Double.parseDouble(scan.nextLine());

        Computer computer = new Computer(brand, model, sn, price);
        for (int j = 0; j < inventory.length; j++) {
            if (inventory[j] == null) {
                inventory[j] = computer;
                System.out.println("Computer added");
                break;
            }
        }
    }

    /**
     * Find all the computers in the inventory by brand name
     * @param brand
     */
    public static void findComputersBy(String brand) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getBrand().equalsIgnoreCase(brand)) {
                displayComputer(i, inventory[i]);
            }
        }
    }

    /**
     * Find all the computers that have less value than the given price
     * @param price
     */
    public static void findCheaperThan(double price) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getPrice() < price) {
                displayComputer(i, inventory[i]);
            }
        }
    }

    /**
     * Display the computer information to the user
     * @param index
     * @param computer
     */
    public static void displayComputer(int index, Computer computer) {
        System.out.println("Computer # " + index);
        System.out.println("Brand: " + computer.getBrand());
        System.out.println("Model: " + computer.getModel());
        System.out.println("SN: " + computer.getSN());
        System.out.println("Price: $" + computer.getPrice());
        System.out.println("--------------------------------");
    }
}