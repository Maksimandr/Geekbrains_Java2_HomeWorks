package lesson1;

/**
 * Перечисление возможных препятствий, содержит название и максимальный размер препятствия
 */
public enum ObstaclesTypes {

    TREADMILL("беговая дорожка", 1000),
    WALL("стена", 100);

    private final String fullName;
    private final int maxSize;

    ObstaclesTypes(String fullName, int maxSize) {
        this.fullName = fullName;
        this.maxSize = maxSize;
    }

    public String getFullName() {
        return fullName;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
