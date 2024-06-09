package five;

import java.time.Duration;

public class HumanReadableTime {

    public static String makeReadable(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String makeReadableEasy(int seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        return String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
    }

    public static void main(String[] args) {
        System.out.println("99:59:59".equals(makeReadable(359999)));
        System.out.println("99:59:59".equals(makeReadableEasy(359999)));
    }
}
