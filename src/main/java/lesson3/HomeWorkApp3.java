package lesson3;

import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных
 * слов, из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
 */

public class HomeWorkApp3 {

    public static void main(String[] args) {

        String[] strings = new String[]{"Создать", "массив", "с", "набором", "слов", "Найти", "и", "вывести",
                "список", "уникальных", "слов", "из", "которых", "состоит", "массив", "массив"};

        System.out.println(Arrays.toString(strings));
        System.out.println(strings.length);

        Map<String, Integer> stringIntegerMap = uniqueArray(strings);

        System.out.println(stringIntegerMap);
        System.out.println(stringIntegerMap.size());
    }

    /**
     * Создает Map, где ключи - это слова из массива, а значения равны количеству их повторений в массиве
     *
     * @param strings массив слов
     * @return
     */
    public static Map uniqueArray(String[] strings) {

        Map<String, Integer> stringIntegerMap = new HashMap();

        // сначала сделал так
//        for (String s: strings) {
//            Integer value = stringIntegerMap.get(s);
//            if (value == null) {
//                stringIntegerMap.put(s, 1);
//            } else {
//                stringIntegerMap.put(s, ++value);
//            }
//        }

        // потом решил посмотреть, как можно сделать проще. Нашел метод merge.
        // Не сразу, но разобрался как работает BiFunction.apply (что для него является аргументами).
//        for (String s : strings) {
//            stringIntegerMap.merge(s, 1, new BiFunction<Integer, Integer, Integer>() {
//                @Override
//                public Integer apply(Integer oldValue, Integer value) {
//                    return oldValue + value;
//                    // в нашем случае можно сделать и так
//                    // return ++oldValue;
//                }
//            });
//        }

        // а потом нашел в интернете способ, как это снабдить синтаксическим сахаром :)
        // правда пока не уверен, что на 100% понимаю, как эта запись работает Integer::sum
        for (String s : strings) {
            stringIntegerMap.merge(s, 1, Integer::sum);
        }
        return stringIntegerMap;
    }
}
