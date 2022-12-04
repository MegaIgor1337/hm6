package factory;

public enum Robot {
    LEFT_HAND,
    RIGHT_HAND,
    LEFT_LEG,
    RIGHT_LEG,
    BODY,
    HEAD;

    private final static Robot[] DETAILS = Robot.values();
    public static Robot nameByNumber(int number) {
        return DETAILS[number];
    }
}
