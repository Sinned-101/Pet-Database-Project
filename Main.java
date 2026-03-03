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
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
                //try to parse the age
                try {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    database.addPet(new Pet(name, age));
                    petsAdded++;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Age must be a number. Please try again.");
                }
            } else {
                System.out.println("Error: Please enter name and age separated by a space.");
            }
        }
        
        System.out.println(petsAdded + " pets added.");
    }
}
