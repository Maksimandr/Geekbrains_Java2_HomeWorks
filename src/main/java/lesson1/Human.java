package lesson1;

/**
 * Класс Человек
 */
public class Human implements Passable {

    private String name; // имя
    private int runLimitValue; // предел для бега
    private int jumpLimitValue; // предел для прыжка

    public Human(String name, int runLimitValue, int jumpLimitValue) {
        this.name = name;
        this.runLimitValue = runLimitValue;
        this.jumpLimitValue = jumpLimitValue;
    }

    public String getName() {
        return name;
    }

    public int getRunLimitValue() {
        return runLimitValue;
    }

    public int getJumpLimitValue() {
        return jumpLimitValue;
    }

    @Override
    public boolean run(Obstacle obstacle) {
        if (isPassable(runLimitValue, obstacle.obstacleSize())) {
            System.out.println(name + " пробежал препятствие (" + obstacle.getObstacleType().getFullName() + ") длинной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
            return true;
        }
        System.out.println(name + " НЕ СМОГ пробежать препятствие длиной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
        return false;
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        if (isPassable(jumpLimitValue, obstacle.obstacleSize())) {
            System.out.println(name + " перепрыгнул препятствие (" + obstacle.getObstacleType().getFullName() + ") высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
            return true;
        }
        System.out.println(name + " НЕ СМОГ перепрыгнуть препятствие высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
        return false;
    }
}

