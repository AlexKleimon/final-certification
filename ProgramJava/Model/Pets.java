package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract public class Pets extends Animals {
    private List<String> commands;
    private String categoryPet;

    public Pets(String nameAnimals, LocalDate dateBirth, String sex, int idAnimals, List<String> commands, String categoryPet) {
        super(nameAnimals, dateBirth, sex, idAnimals);
        this.commands = new ArrayList<>(commands);
        this.categoryPet = categoryPet;
    }

    public List<String> getCommands() {
        return commands;
    }
    public String getCategoryPet() {
        return categoryPet;
    }
    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
// подумать где лучше добавлять команду
    public void addCommand(String command) {
        commands.add(command);
    }

    public void delCommand(String command) {
        commands.remove(command);
    }
}
