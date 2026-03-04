import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

//stores all the pets
public class PetDatabase {
    //file path for saving/loading pets
    private static final String FILE_PATH = "File/pets.txt";
    private ArrayList<Pet> pets;

    //constructor
    public PetDatabase() {
        pets = new ArrayList<>();
    }

    //add a pet to the list
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    //show all pets in a table
    public void showAllPets() {
        System.out.println("+----------------------+");
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %3d | %-10s | %4d |%n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    //search pets by name
    public void searchByName(String name) {
        System.out.println("+----------------------+");
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        
        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            //check if name matches (case insensitive)
            if (pets.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                System.out.printf("| %3d | %-10s | %4d |%n", i, pets.get(i).getName(), pets.get(i).getAge());
                count++;
            }
        }
        
        System.out.println("+----------------------+");
        System.out.println(count + " rows in set.");
    }

    //search pets by age
    public void searchByAge(int age) {
        System.out.println("+----------------------+");
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        
        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getAge() == age) {
                System.out.printf("| %3d | %-10s | %4d |%n", i, pets.get(i).getName(), pets.get(i).getAge());
                count++;
            }
        }
        
        System.out.println("+----------------------+");
        System.out.println(count + " rows in set.");
    }

    //update a pet by id
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            pets.get(id).setName(newName);
            pets.get(id).setAge(newAge);
        }
    }

    //remove a pet by id
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        }
    }

    //get number of pets
    public int getPetCount() {
        return pets.size();
    }

    //load pets from file
    public void loadExistingPets() {
        try {
            Scanner fileReader = new Scanner(new File(FILE_PATH));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets.add(new Pet(name, age));
                }
            }
            fileReader.close();
            System.out.println("Pet data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data. Starting fresh.");
        }
    }

    //save pets to file
    public void saveNewPets() {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH);
            for (int i = 0; i < pets.size(); i++) {
                writer.println(pets.get(i).getName() + " " + pets.get(i).getAge());
            }
            writer.close();
            System.out.println("Pet data saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving to file.");
        }
    }
}
