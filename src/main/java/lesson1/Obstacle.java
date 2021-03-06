package lesson1;

/**
 * Класс обязывает все препятствия показывать к какому типу они принадлежат и свою величину, характеризующую размер
 * препятствия (длину, высоту и т.д.) *
 */
abstract public class Obstacle {

    protected ObstaclesTypes obstacleType;

    public ObstaclesTypes getObstacleType() {
        return obstacleType;
    }

    abstract public int obstacleSize();
}
