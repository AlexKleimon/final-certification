package Model;

import java.time.LocalDate;
import java.util.List;

public class Dog extends Pets {
    public Dog(String nameAnimals, LocalDate dateBirth, String sex, int idAnimals, List<String> commands,
            String categoryPet) {
        super(nameAnimals, dateBirth, sex, idAnimals, commands, categoryPet);
    }
}
