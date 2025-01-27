package Model;

import java.time.LocalDate;
import java.util.List;

public class Hamester extends Pets {
    public Hamester(String nameAnimals, LocalDate dateBirth, String sex, int idAnimals, List<String> commands,
            String categoryPet) {
        super(nameAnimals, dateBirth, sex, idAnimals, commands, categoryPet);
    }
}
