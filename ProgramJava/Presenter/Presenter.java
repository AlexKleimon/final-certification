package Presenter;

import Model.RegistoryPets;
import View.InputData;
import View.OutputData;
import View.Validation;

public class Presenter {
    protected RegistoryPets rPets;
    protected InputData iData;
    protected OutputData oData;
    protected Validation val;

    public Presenter(RegistoryPets rPets, InputData iData, OutputData oData) {
        this.rPets = rPets;
        this.iData = iData;
        this.oData = oData;
        this.val = new Validation(iData);
    }

    public void startPrg() throws Exception {
        boolean flag = true;
        try (Counter id = new Counter()) {
            while (flag) {
                System.out.println(
                        "Список команд: add - добавить питомца в список, del - удалить питомца из списка, all - посмотреть список питомцев,"
                                +
                                "addCom - добавить команду питомцу, delCom - удалить команду питомца, save - сохранить изменения, quant - количество питомцев, exit - для выхода");
                switch (iData.setNameSexAndText("Введите команду")) {
                    case "add" -> rPets.addPet(iData.setNameSexAndText("Введите имя питомца"),
                            val.setDataBirth("Введите дату рождения питомца (формат: 31-01-2024)"),
                            iData.setNameSexAndText("Введите пол питомца"), id.add(),
                            iData.setCommandsPet("Введите команды питомца, через запятую"),
                            iData.setNameSexAndText("Ввидите вид питомца (cat, dog, hamester)"));
                    case "all" -> oData.outPutPets(rPets.getAllPets());
                    case "del" -> rPets.delPets(iData.setAnimalsId("Введите id питомца"));
                    case "addCom" -> rPets.addCommand(iData.setAnimalsId("Введите id питомца"),
                            iData.setNameSexAndText("Введите команду питомца"));
                    case "delCom" -> rPets.delCommand(iData.setAnimalsId("Введите id питомца"),
                            iData.setNameSexAndText("Введите команду питомца"));
                    case "save" -> rPets.saveListPets();
                    case "quant" -> System.out.println(Counter.getSum());
                    case "exit" -> flag = false;
                    default -> System.out.println("Данная команда не существует.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error");
        }

    }
}
