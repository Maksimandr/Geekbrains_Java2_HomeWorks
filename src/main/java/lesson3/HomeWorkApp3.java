package lesson3;

import java.util.*;
import java.util.function.BiFunction;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных
 * слов, из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот
 * телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона
 * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда
 * при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
 * дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль
 * желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */
public class HomeWorkApp3 {

    public static void main(String[] args) {

        String[] strings = new String[]{"Создать", "массив", "с", "набором", "слов", "Найти", "и", "вывести",
                "список", "уникальных", "слов", "из", "которых", "состоит", "массив", "массив"};

        System.out.println(Arrays.toString(strings));
        System.out.println(strings.length);

        uniqueArray(strings);
    }

    public static void uniqueArray(String[] strings) {

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

        // а потом нашел в интернете способ как это снабдить синтаксическим сахаром :)
        for (String s : strings) {
            stringIntegerMap.merge(s, 1, Integer::sum);
        }

        System.out.println(stringIntegerMap);
        System.out.println(stringIntegerMap.size());
    }

}
