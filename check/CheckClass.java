package check;

public class CheckClass {
    private volatile boolean check;
    public CheckClass() {
        check = true;
    }

    public void changeCheckOnFalse() {
        this.check = false;
    }

    public boolean isCheck() {
        return check;
    }
}
