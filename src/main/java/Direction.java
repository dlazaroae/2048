public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction fromVal(int val) {
        switch (val) {
            case 4:
                return LEFT;
            case 6:
                return RIGHT;
            case 8:
                return UP;
            case 5:
                return DOWN;
            default:
                return LEFT;
        }
    }
}
