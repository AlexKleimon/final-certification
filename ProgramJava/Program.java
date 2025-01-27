import Model.RegistoryPets;
import Presenter.Presenter;
import View.InputData;
import View.OutputData;

public class Program {
    public static void main(String[] args) throws Exception {
        Presenter p = new Presenter(new RegistoryPets(), new InputData(), new OutputData());
        p.startPrg();
    }
}
