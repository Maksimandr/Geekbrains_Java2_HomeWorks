package lesson1;

/**
 * Класс Кот
 */
public class Cat implements Passable {

    private String name; // кличка
    private int runLimitValue; // предел для бега
    private int jumpLimitValue; // предел для прыжка

    public Cat(String name, int runLimitValue, int jumpLimitValue) {
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
