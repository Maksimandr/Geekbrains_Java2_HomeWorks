package lesson2;

public class MyArrayDataException extends RuntimeException{

    public MyArrayDataException(String s, NumberFormatException e) {
        super(s + e);
    }
}
