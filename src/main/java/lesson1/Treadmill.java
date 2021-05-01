package lesson1;

/**
 * Класс беговой дорожки, в качестве размера препятствия возвращает длину
 */
public class Treadmill extends Obstacle {

    private final int treadmillLength;

    public Treadmill(int value) {
        if (value < 0 || value > ObstaclesTypes.TREADMILL.getMaxSize()) {
            throw new IllegalArgumentException("Некорректно задана длина беговой дорожки");
        }
        this.treadmillLength = value;
        this.obstacleType = ObstaclesTypes.TREADMILL;
    }

    @Override
    public int obstacleSize() {
        return treadmillLength;
    }
}
