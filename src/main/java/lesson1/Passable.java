package lesson1;

/**
 * Интерфейс обязывает реализовывать методы бега и прыжка, а токже реализует метод проверки возможности
 * преодолеть препятствие
 */
public interface Passable {

    boolean run(Obstacle obstacle);

    boolean jump(Obstacle obstacle);

    default boolean isPassable(int limitValue, int obstacleValue) {
        if (limitValue < obstacleValue) {
            return false;
        } else {
            return true;
        }
    }
}
