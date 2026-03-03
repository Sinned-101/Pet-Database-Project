import java.util.ArrayList;

//stores all the pets
public class PetDatabase {
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
}
