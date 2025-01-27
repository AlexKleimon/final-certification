package Model;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistoryPets {
    private List<Pets> allPets = new ArrayList<>();

    public List<Pets> getAllPets() {
        return allPets;
    }

    private void addCat(String name, LocalDate dateBirth, String sex, int idAnimals, List<String> command,
            String categoryPet) {
        allPets.add(new Cat(name, dateBirth, sex, idAnimals, command, categoryPet));
    }

    private void addDog(String name, LocalDate dateBirth, String sex, int idAnimals, List<String> command,
            String categoryPet) {
        allPets.add(new Dog(name, dateBirth, sex, idAnimals, command, categoryPet));
    }

    private void addHamester(String name, LocalDate dateBirth, String sex, int idAnimals, List<String> command,
            String categoryPet) {
        allPets.add(new Hamester(name, dateBirth, sex, idAnimals, command, categoryPet));
    }

    public void addPet(String name, LocalDate dateBirth, String sex, int idAnimals, List<String> command,
            String categoryPet) {
        switch (categoryPet.toLowerCase()) {
            case "cat" -> addCat(name, dateBirth, sex, idAnimals, command, categoryPet);
            case "dog" -> addDog(name, dateBirth, sex, idAnimals, command, categoryPet);
            case "hamester" -> addHamester(name, dateBirth, sex, idAnimals, command, categoryPet);
            default -> System.out.println(
                    "Данного питомца невозможно добавить в базу данных. Перчень питомцев: cat, dog, hamester.");
        }
    }

    public void delPets(int idAnimals) {
        int count = 0;
        for (Pets pets : allPets) {
            if (pets.getIdAnimals() == idAnimals) {
                allPets.remove(count);
                break;
            }
            count++;
        }
    }

    public void addCommand(int idAnimals, String command) {
        for (Pets pets : allPets) {
            if (pets.getIdAnimals() == idAnimals) {
                pets.addCommand(command);
                break;
            }
        }
    }

    public void delCommand(int idAnimals, String command) {
        for (Pets pets : allPets) {
            if (pets.getIdAnimals() == idAnimals) {
                pets.delCommand(command);
                break;
            }
        }
    }

    public void saveListPets() throws Exception {
        File csvFile = new File("Pets.csv");
        FileWriter writer = new FileWriter(csvFile, false);
        if (csvFile.length() == 0)
            writer.write("idPet;name;sex;dateBirth;commands;categoryPet;\n");
        for (Pets aP : allPets) {
            String text = String.format("%d;%s;%s;%s;%s;%s;\n",
                    aP.getIdAnimals(), aP.getNameAnimals(), aP.getSex(), aP.getDateBirth(),
                    aP.getCommands().toString(), aP.getCategoryPet());
            writer.write(text);
        }
        writer.close();
        System.out.println("Данные успешно сохранены.");
    }
}
