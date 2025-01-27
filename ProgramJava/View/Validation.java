package View;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Validation {
    protected InputData iData;

    public Validation(InputData iData) {
        this.iData = iData;
    }

    public LocalDate setDataBirth(String title) {
        boolean flag = true;
        LocalDate result = LocalDate.now();
        while (flag) {
            try {
                result = iData.setDataBirth(title);
                flag = false;
            } catch (DateTimeParseException | InputMismatchException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Неверный формат даты. Повторите попытку.");
            }
        }
        return result;
    }
}
