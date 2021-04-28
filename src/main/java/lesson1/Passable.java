package lesson1;

/**
 * Интерфейс обязывает реализовывать методы бега и прыжка, а токже реализует метод определяющий способ преодоления и
 * метод проверки возможности преодолеть препятствие
 */
public interface Passable {

    boolean run(Obstacle obstacle);

    boolean jump(Obstacle obstacle);

    /**
     * Определяет тип препятствия и соответственно каким методом его преодолевать
     * @param obstacle
     * @return
     */
    default boolean passAnObstacle(Obstacle obstacle) {

        if (obstacle.getObstacleType() == ObstaclesTypes.TREADMILL) {
            return this.run(obstacle); // по беговой дорожке бежим
        } else if (obstacle.getObstacleType() == ObstaclesTypes.WALL) {
            return this.jump(obstacle); // через стену прыгаем
        } else {
            throw new IllegalArgumentException("Неизвестный тип препятствия."); // такого по идее не должно случаться, но пусть будет :)
        }
    }

    /**
     * Проверка на возможность преодолеть препятствие
     * @param limitValue
     * @param obstacleValue
     * @return
     */
    default boolean isPassable(int limitValue, int obstacleValue) {
        return limitValue >= obstacleValue;
    }

    /**
     * Эти методы реализовал чтобы не повторять код, если обработка бега и прыжка у классов человека, кота и робота
     * должна быть разная, то их реализация не нужна, а обработка вывода будет в методах бега и прыжка в своих классах.
     * @param name
     * @param obstacle
     * @param limitValue
     * @return
     */
    default boolean printResultForRun(String name, Obstacle obstacle, int limitValue) {
        if (isPassable(limitValue, obstacle.obstacleSize())) {
            System.out.println(name + " пробежал препятствие (" + obstacle.getObstacleType().getFullName() + ") длинной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + limitValue + ")");
            return true;
        }
        System.out.println(name + " НЕ СМОГ пробежать препятствие длиной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + limitValue + ")");
        return false;
    }

    default boolean printResultForJump(String name, Obstacle obstacle, int limitValue) {
        if (isPassable(limitValue, obstacle.obstacleSize())) {
            System.out.println(name + " перепрыгнул препятствие (" + obstacle.getObstacleType().getFullName() + ") высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + limitValue + ")");
            return true;
        }
        System.out.println(name + " НЕ СМОГ перепрыгнуть препятствие высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + limitValue + ")");
        return false;
    }
}
