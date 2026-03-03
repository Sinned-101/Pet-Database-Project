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
}
