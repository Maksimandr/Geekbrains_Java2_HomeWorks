package lesson2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int i, int j, NumberFormatException e) {
        super(String.format("В ячейке массива с индексом [%d][%d] неверный формат данных. %s", i, j, e));
    }
}
