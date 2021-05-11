package lesson5;

/**
 * Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив
 * static final int SIZE = 10_000_000;
 * static final int HALF = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами.
 * 3) Засекают время выполнения: long a = System.currentTimeMillis().
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis().
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
 * Отличие первого метода от второго:
 * - Первый просто бежит по массиву и вычисляет значения.
 * - Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 */

public class HomeWorkApp5 {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        firstMethod(); // время вычислений равно 1010 - 1040
        secondMethod(); // время вычислений равно 480 - 520
    }

    public static void firstMethod() {
        // создаем массив и заполняем единицами
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        // засекаем время1
        long t1 = System.currentTimeMillis();
        // считаем новые значения массива
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            ;
        }
        // засекаем время2
        long t2 = System.currentTimeMillis();
        // выводим время выполнения
        System.out.println(t2 - t1);
    }

    public static void secondMethod() throws InterruptedException {
        // создаем массивы и заполняем единицами
        float[] arr = new float[SIZE];
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        // засекаем время1
        long t1 = System.currentTimeMillis();
        // разбиваем массив на два
        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);
        // создаем два потока для вычислений
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    ;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    ;
                }
            }
        });
        // запускаем потоки
        thread1.start();
        thread2.start();
        // ждем их выполнения
        thread1.join();
        thread2.join();
        // склеиваем массив
        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);
        // засекаем время2
        long t2 = System.currentTimeMillis();
        // выводим время выполнения
        System.out.println(t2 - t1);
    }
}
