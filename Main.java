import java.util.Scanner;

//main class for pet database program
public class Main {
    private static PetDatabase database = new PetDatabase();
    private static Scanner scanner = new Scanner(System.in);

    //main method
    public static void main(String[] args) {
        //load pets from file on startup
        database.loadExistingPets();
        
        int choice;
        do {
            //print menu
            System.out.println("Pet Database Program.");
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add new pets");
            System.out.println("3) Search pets");
            System.out.println("4) Update a pet");
            System.out.println("5) Remove a pet");
            System.out.println("6) Exit program");
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
                case 4:
                    updatePet();
                    break;
                case 5:
                    removePet();
                    break;
                case 6:
                    //save pets to file before exiting
                    database.saveNewPets();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);
        
        scanner.close();
    }

    //method to add pets
    private static void addPets() {
        int petsAdded = 0;
        
        //keep reading until done
        while (true) {
            System.out.print("add pet (name, age): ");
            String input = scanner.nextLine();
            
            if (input.equals("done")) {
                break;
            }
            
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Error: " + input + " is not a valid input.");
                continue;
            }
            
            //try to parse the age an int.
            try {
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                
                //validate age range
                if (age < 1 || age > 20) {
                    System.out.println("Error: " + age + " is not a valid age.");
                    continue;
                }
                
                //check if database is full
                if (database.databaseFull()) {
                    System.out.println("Error: Database is full.");
                    break;
                }
                
                database.addPet(new Pet(name, age));
                petsAdded++;
            } catch (NumberFormatException e) {
                System.out.println("Error: " + input + " is not a valid input.");
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

    //update a pet
    private static void updatePet() {
        //show all pets first
        database.showAllPets();
        
        System.out.print("Enter the pet ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        //check if id is valid
        if (id < 0 || id >= database.getPetCount()) {
            System.out.println("Error: ID " + id + " does not exist.");
            return;
        }
        
        System.out.print("Enter new name and new age: ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        
        if (parts.length == 2) {
            try {
                String newName = parts[0];
                int newAge = Integer.parseInt(parts[1]);
                
                //validate age range
                if (newAge < 1 || newAge > 20) {
                    System.out.println("Error: " + newAge + " is not a valid age.");
                    return;
                }
                
                database.updatePet(id, newName, newAge);
                System.out.println("Pet updated.");
            } catch (NumberFormatException e) {
                System.out.println("Error: " + input + " is not a valid input.");
            }
        } else {
            System.out.println("Error: " + input + " is not a valid input.");
        }
    }

    //remove a pet
    private static void removePet() {
        //show all pets first
        database.showAllPets();
        
        System.out.print("Enter the pet ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        //check if id is valid
        if (id < 0 || id >= database.getPetCount()) {
            System.out.println("Error: ID " + id + " does not exist.");
            return;
        }
        
        //get pet info before removing
        String name = database.getPetName(id);
        int age = database.getPetAge(id);
        database.removePet(id);
        System.out.println(name + " " + age + " is removed.");
    }
}
