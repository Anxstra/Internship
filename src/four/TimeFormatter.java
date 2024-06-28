package four;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeFormatter {

    public static String formatDurationUpdated(int seconds) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("year", formatTimeUnit("year", (seconds / 31536000)));
        map.put("day", formatTimeUnit("day", (seconds / 86400) % 365));
        map.put("hour", formatTimeUnit("hour", (seconds / 3600) % 24));
        map.put("minute", formatTimeUnit("minute", (seconds / 60) % 60));
        map.put("second", formatTimeUnit("second", (seconds % 3600) % 60));

        if (seconds == 0) {
            return "now";
        }

        return map.values()
                  .stream()
                  .filter(e -> !e.isEmpty())
                  .collect(Collectors.joining(", "))
                  .replaceAll(", (?!.+,)", " and ");
    }

    public static String formatTimeUnit(String s, int time) {
        return time == 0 ? "" : time + " " + s + isPlural(time);
    }

    private static String isPlural(int time) {
        return time > 1 ? "s" : "";
    }

    public static void main(String[] args) {

        System.out.println("3 years, 85 days, 1 hour, 9 minutes and 26 seconds".equals(formatDurationUpdated(101956166)));
        System.out.println(("1 year, 19 days, 18 hours, 19 minutes and 46 seconds".equals(formatDurationUpdated(33243586))));
    }
}
