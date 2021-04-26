package lesson1;

/**
 * Класс Кот
 */
public class Cat implements Passable {

    private String nickname; // кличка
    private int runLimitValue; // предел для бега
    private int jumpLimitValue; // предел для прыжка

    public Cat(String nickname, int runLimitValue, int jumpLimitValue) {
        this.nickname = nickname;
        this.runLimitValue = runLimitValue;
        this.jumpLimitValue = jumpLimitValue;
    }

    public String getNickname() {
        return nickname;
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
            System.out.println(nickname + " пробежал препятствие (" + obstacle.getObstacleType().getFullName() + ") длинной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
            return true;
        }
        System.out.println(nickname + " НЕ СМОГ пробежать препятствие длиной " + obstacle.obstacleSize() + ". (максимальная дистанция бега = " + runLimitValue + ")");
        return false;
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        if (isPassable(jumpLimitValue, obstacle.obstacleSize())) {
            System.out.println(nickname + " перепрыгнул препятствие (" + obstacle.getObstacleType().getFullName() + ") высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
            return true;
        }
        System.out.println(nickname + " НЕ СМОГ перепрыгнуть препятствие высотой " + obstacle.obstacleSize() + ". (максимальная высота прыжка = " + jumpLimitValue + ")");
        return false;
    }
}
