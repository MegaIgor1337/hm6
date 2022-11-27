package factory;

public enum Robot {
    LEFT_HAND(1),
    RIGHT_HAND(2),
    LEFT_LEG(3),
    RIGHT_LEG(4),
    BODY(5),
    HEAD(6);

    private final Integer number;

    Robot(int number) {
        this.number = number;
    }

    public static Robot nameByNumber(int number) {
        Robot[] details = Robot.values();
        for (Robot r: details) {
            if (number == r.number) {
                return r;
            }
        }
        return null;
    }
}
