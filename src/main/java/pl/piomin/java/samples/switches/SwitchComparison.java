package pl.piomin.java.samples.switches;

public class SwitchComparison {

    public String oldSwitch(int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            default:
                return "other";
        }
    }

    public String newSwitch(int number) {
        return switch (number) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            default -> "other";
        };
    }

    public String oldMultiSwitch(int day) {
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return "workday";
            case 6:
            case 7:
                return "weekend";
            default:
                return "invalid";
        }
    }

    public String newMultiSwitch(int day) {
        return switch (day) {
            case 1, 2, 3, 4, 5 -> "workday";
            case 6, 7 -> "weekend";
            default -> "invalid";
        };
    }

    public String oldSwitchWithBreak(int number) {
        String word = "other";
        switch (number) {
            case 1:
                word = "one";
                break;
            case 2:
                word = "two";
                break;
            case 3:
                word = "three";
                break;
        }
        return word;
    }


}
