package lesson3;

import java.util.*;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот
 * телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона
 * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда
 * при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
 * дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль
 * желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */

public class PhoneBook {

    // выбрал в Map HashSet для того, чтобы исключить варианты когда у одной фамилии есть несколько одинаковых номеров,
    // если такие варианты не нужно отсекать, то нужно использовать ArrayList
    private Map<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    /**
     * Добавляет Фамилию и номер
     *
     * @param lastName    фамилия
     * @param phoneNumber номер
     * @return true если произошло добавление фамилия+номер или просто номер
     */
    public boolean add(String lastName, String phoneNumber) {
        if (phoneBook.containsKey(lastName)) {
            return phoneBook.get(lastName).add(phoneNumber);
        }
        phoneBook.put(lastName, new HashSet<>(Collections.singleton(phoneNumber)));
        return true;
    }

    public Set<String> get(String lastname) {
        // я написал так,  а идея предложила второй вариант
//        if (phoneBook.containsKey(lastname)) {
//            return phoneBook.get(lastname);
//        }
//        return null;
        return phoneBook.getOrDefault(lastname, null);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", "89000000000");
        phoneBook.add("Ivanov", "89000000001");
        phoneBook.add("Ivanov", "89000000002");

        phoneBook.add("Petrov", "89000000003");
        phoneBook.add("Petrov", "89000000004");

        phoneBook.add("Egorov", "89000000005");

        phoneBook.add("Sidorov", "89000000006");
        phoneBook.add("Sidorov", "89000000006");

        System.out.println(phoneBook);

        // у Ivanov будет три номера
        System.out.println(phoneBook.get("Ivanov"));
        // у Petrov будет два номера
        System.out.println(phoneBook.get("Petrov"));
        // у Egorov будет один номер
        System.out.println(phoneBook.get("Egorov"));
        // у Sidorov будет один номер (при реализации через Set)
        System.out.println(phoneBook.get("Sidorov"));
        // у Doe вывод равен null
        System.out.println(phoneBook.get("Doe"));
    }
}
