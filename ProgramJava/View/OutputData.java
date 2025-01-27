package View;

import java.util.List;

import Model.Pets;

public class OutputData {
    public void outPutPets(List<Pets> allPets) {
        for (Pets p : allPets) {
            System.out.println(String.format("id: %d, name: %s, sex: %s, date birth: %s, commands: %s, category: %s",
                    p.getIdAnimals(), p.getNameAnimals(), p.getSex(), p.getDateBirth(), p.getCommands(),
                    p.getCategoryPet()));
        }
    }

}
