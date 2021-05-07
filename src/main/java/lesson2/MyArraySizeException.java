package lesson2;

public class MyArraySizeException extends RuntimeException{

    public MyArraySizeException(int arraySizeX, int arraySizeY) {
        super(String.format("Аргументом может быть только двумерный массив размером %dх%d", arraySizeX, arraySizeY));
    }
}
