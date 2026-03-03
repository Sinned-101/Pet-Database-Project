import java.util.Scanner;

//main class for pet database program
public class Main {
    private static PetDatabase database = new PetDatabase();
    private static Scanner scanner = new Scanner(System.in);

    //main method
    public static void main(String[] args) {
        int choice;
        do {
            //print menu
            System.out.println();
            System.out.println("Main Menu");
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Search pets");
            System.out.println("0) Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            //Switch statement handles user menu choice
            switch (choice) {
                case 1:
                    database.showAllPets();
                    break;
                case 2:
                    addPets();
                    break;
                case 3:
                    searchPets();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }

    //method to add pets
    private static void addPets() {
        System.out.println("Add pets (name age). Type 'done' when finished:");
        int petsAdded = 0;
        
        //keep reading until done
        while (true) {
            System.out.print("add pet (name age): ");
            String input = scanner.nextLine();
            
            if (input.equals("done")) {
                break;
            }
            
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                //try to parse the age an int.
                try {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    database.addPet(new Pet(name, age));
                    petsAdded++;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Age must be a number.");
                }
            } else {
                System.out.println("Error: Please enter name and age separated by a space.");
            }
        }
        
        System.out.println(petsAdded + " pets added.");
    }

    //search for pets by name or age
    private static void searchPets() {
        System.out.print("Enter a name or age to search: ");
        String input = scanner.nextLine();
        
        //check if input is a number
        try {
            int age = Integer.parseInt(input);
            database.searchByAge(age);
        } catch (NumberFormatException e) {
            //not a number so search by name
            database.searchByName(input);
        }
    }
}
