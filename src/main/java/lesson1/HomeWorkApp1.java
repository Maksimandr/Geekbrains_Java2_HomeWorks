package lesson1;

import java.util.Random;

/**
 * 1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и
 * прыгать (методы просто выводят информацию о действии в консоль).
 * 2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять
 * соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог
 * пробежать и т.д.).
 * 3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
 * 4.* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если
 * участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 */

public class HomeWorkApp1 {

    private static Random random = new Random();

    public static void main(String[] args) {

        int maxParticipants = 20; // количество участников
        int maxTypesOfParticipants = 3; // количество разных типов участников (люди, коты, роботы..)
        int maxQuantityOfObstacles = 10; // количество препятствий на полосе препятствий

        Passable[] participants = new Passable[maxParticipants];
        Obstacle[] obstacles = new Obstacle[maxQuantityOfObstacles];

        // заполняем массив участников случайным набором разных типов
        for (int i = 0; i < maxParticipants; i++) {
            switch (random.nextInt(maxTypesOfParticipants)) {
                case 0: {
                    participants[i] = new Human("Человек №" + i, random.nextInt(ObstaclesTypes.TREADMILL.getMaxSize()), random.nextInt(ObstaclesTypes.WALL.getMaxSize()));
                    break;
                }
                case 1: {
                    participants[i] = new Cat("Кот №" + i, random.nextInt(ObstaclesTypes.TREADMILL.getMaxSize()), random.nextInt(ObstaclesTypes.WALL.getMaxSize()));
                    break;
                }
                default: {
                    participants[i] = new Robot("Робот №" + i, random.nextInt(ObstaclesTypes.TREADMILL.getMaxSize()), random.nextInt(ObstaclesTypes.WALL.getMaxSize()));
                }
            }
        }

        // заполняем массив препятствий случайным набором разных типов
        for (int i = 0; i < maxQuantityOfObstacles; i++) {
            switch (random.nextInt(ObstaclesTypes.values().length)) {
                case 0: {
                    obstacles[i] = new Treadmill(random.nextInt(ObstaclesTypes.TREADMILL.getMaxSize() - 700)); // специально для удобства тестирования уменьшил, чтобы чаще выпадали малые значения длины
                    break;
                }
                default: {
                    obstacles[i] = new Wall(random.nextInt(ObstaclesTypes.WALL.getMaxSize() - 70)); // специально для удобства тестирования уменьшил, чтобы чаще выпадали малые значения высоты
                }
            }
        }

        // прогоняем участников через полосу препятствий
        for (int i = 0; i < maxParticipants; i++) {
            for (int j = 0; j < maxQuantityOfObstacles; j++) {
                if (!participants[i].passAnObstacle(obstacles[j])) {
                    break; // если препятствие не преодолено участник снимается с пробега
                }
            }
            System.out.println(); // отделяем вывод следующего участника
        }
    }
}
