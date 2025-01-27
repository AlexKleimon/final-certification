package Presenter;

public class Counter implements AutoCloseable {
    static private int sum = 0;

    public static int getSum() {
        return sum;
    }

    public int add() {
        return sum++;
    }

    @Override
    public void close() {
        System.out.println("Счетчик закрыт.");
    }
}
