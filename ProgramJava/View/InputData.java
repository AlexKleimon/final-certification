package View;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class InputData {
    protected Scanner sc = new Scanner(System.in);

    public String setNameSexAndText(String title) {
        System.out.printf("%s: ", title);
        return sc.nextLine();
    }

    public int setAnimalsId(String title) {
        System.out.printf("%s: ", title);
        int value;
        if (sc.hasNextInt()) {
            value = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Ошибка!Ведите целое число!");
            sc.nextLine();
            value = setAnimalsId(title);
        }
        return value;
    }

    public LocalDate setDataBirth(String title) {
        System.out.printf("%s: ", title);
        String[] inputStr = sc.nextLine().split("-");
        return LocalDate.of(Integer.parseInt(inputStr[2]), Integer.parseInt(inputStr[1]),
                Integer.parseInt(inputStr[0]));
    }

    public List<String> setCommandsPet(String title) {
        System.out.printf("%s: ", title);
        List<String> lsCmd = new ArrayList<>();
        String[] inputStr = sc.nextLine().split(",");
        for (String s : inputStr) {
            lsCmd.add(s);
        }
        return lsCmd;
    }

}
