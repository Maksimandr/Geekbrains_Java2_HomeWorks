package lesson1;

/**
 * Класс обязывает все препятствия показывать к какому типу они принадлежат и свой размер
 */
abstract public class Obstacle {

    protected ObstaclesTypes obstacleType;

    public ObstaclesTypes getObstacleType() {
        return obstacleType;
    }

    abstract public int obstacleSize();
}
