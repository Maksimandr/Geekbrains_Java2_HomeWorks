package lesson1;

/**
 * Класс стены, в качестве размера препятствия возвращает высоту
 */
public class Wall extends Obstacle {

    private final int wallHeight;

    public Wall(int value) {
        if (value < 0 || value > ObstaclesTypes.WALL.getMaxSize()) {
            throw new IllegalArgumentException("Некорректно задана высота стены");
        }
        this.wallHeight = value;
        this.obstacleType = ObstaclesTypes.WALL;
    }

    @Override
    public int obstacleSize() {
        return wallHeight;
    }
}
