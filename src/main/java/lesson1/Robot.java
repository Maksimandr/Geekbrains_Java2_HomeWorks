package lesson1;

/**
 * Класс Робот
 */
public class Robot implements Passable {

    private String model; // модель
    private int runLimitValue; // предел для бега
    private int jumpLimitValue; // предел для прыжка

    public Robot(String model, int runLimitValue, int jumpLimitValue) {
        this.model = model;
        this.runLimitValue = runLimitValue;
        this.jumpLimitValue = jumpLimitValue;
    }

    public String getModel() {
        return model;
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
            System.out.println(model + " пробежал препятствие (" + obstacle.getObstacleType().getFullName() + ") длинной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
            return true;
        }
        System.out.println(model + " НЕ СМОГ пробежать препятствие длиной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
        return false;
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        if (isPassable(jumpLimitValue, obstacle.obstacleSize())) {
            System.out.println(model + " перепрыгнул препятствие (" + obstacle.getObstacleType().getFullName() + ") высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
            return true;
        }
        System.out.println(model + " НЕ СМОГ перепрыгнуть препятствие высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
        return false;
    }
}
