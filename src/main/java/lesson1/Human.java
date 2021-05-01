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
        return printResultForRun(name, obstacle, runLimitValue);
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        return printResultForJump(name, obstacle, jumpLimitValue);
    }
}

