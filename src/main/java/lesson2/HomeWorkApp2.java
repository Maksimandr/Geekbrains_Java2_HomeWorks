package lesson2;

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого
 * размера необходимо бросить исключение MyArraySizeException.
 * <p>
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то
 * элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
 * брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 * <p>
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
 * MyArrayDataException, и вывести результат расчета.
 */

public class HomeWorkApp2 {

    private static final int ARRAY_SIZE = 4; // размер для квадратного массива

    public static void main(String[] args) {

        // массивы для тестов
        // квадратный массив 4х4, заполненный целыми числами, сумма всех чисел равна 440
        String[][] myArray1 = new String[][]
                {{"11", "12", "13", "14"},
                        {"21", "22", "23", "24"},
                        {"31", "32", "33", "34"},
                        {"41", "42", "43", "44"}};

        // массив 3х4
        String[][] myArray2 = new String[][]
                {{"11", "12", "13", "14"},
                        {"21", "22", "23", "24"},
                        {"31", "32", "33", "34"}};

        // массив, в котором одна из строк имеет длинну 3
        String[][] myArray3 = new String[][]
                {{"11", "12", "13", "14"},
                        {"21", "22", "23", "24"},
                        {"31", "32", "33"},
                        {"41", "42", "43", "44"}};

        // массив, в котором одна из ячеек содержит символы
        String[][] myArray4 = new String[][]
                {{"11", "12", "13", "14"},
                        {"21", "22", "AAAAAAAAAAA", "24"},
                        {"31", "32", "33", "34"},
                        {"41", "42", "43", "44"}};

        // считаем и выводим в консоль сумму элементов массива, обрабатываем возможные исключения
        try {
            System.out.println("Сумма равна " + myArraySum(myArray4));
        } catch (MyArraySizeException e) {
            // как-то обрабатываем MyArraySizeException
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            // как-то обрабатываем MyArrayDataException
            e.printStackTrace();
        }
    }

    /**
     * Считает сумму всех элементов массива
     *
     * @param array обрабатываемый массив
     * @return сумма всех элементов массива
     * @throws MyArraySizeException если массив не квадратный с размером ARRAY_SIZE
     * @throws MyArrayDataException если в ячейке массива не число типа int
     */
    public static int myArraySum(String[][] array) {

        if (!isSquare(array, ARRAY_SIZE)) {
            throw new MyArraySizeException(ARRAY_SIZE, ARRAY_SIZE);
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, e);
                }
            }
        }
        return sum;
    }

    /**
     * Проверяет, что массив квадратный
     *
     * @param arr  обрабатываемый массив
     * @param size требуемый размер массива
     * @return true - квадратный
     */
    public static boolean isSquare(String[][] arr, int size) {
        if (arr.length != size) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != size) {
                return false;
            }
        }
        return true;
    }
}
