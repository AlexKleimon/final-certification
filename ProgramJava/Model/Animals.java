package Model;

import java.time.LocalDate;

abstract public class Animals {
    private String nameAnimals;
    private LocalDate dateBirth;
    private String sex;
    private int idAnimals;

    public Animals(String nameAnimals, LocalDate dateBirth, String sex, int idAnimals) {
        this.nameAnimals = nameAnimals;
        this.dateBirth = dateBirth;
        this.sex = sex;
        this.idAnimals = idAnimals;
    }

    public void setNameAnimals(String nameAnimals) {
        this.nameAnimals = nameAnimals;
    }

    public String getNameAnimals() {
        return nameAnimals;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdAnimals() {
        return idAnimals;
    }

}
