package four;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TimeFormatter {

    public static String formatDurationUpdated(int seconds) {
        return seconds == 0 ? "now" :
                Arrays.stream(
                              new String[]{
                                      formatTimeUnit("year", (seconds / 31536000)),
                                      formatTimeUnit("day", (seconds / 86400) % 365),
                                      formatTimeUnit("hour", (seconds / 3600) % 24),
                                      formatTimeUnit("minute", (seconds / 60) % 60),
                                      formatTimeUnit("second", (seconds % 3600) % 60)})
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

    public static String formatDuration(int seconds) {
        if (seconds == 0) return "now";

        int years = seconds / (365 * 24 * 3600);
        seconds %= (365 * 24 * 3600);
        int days = seconds / (24 * 3600);
        seconds %= (24 * 3600);
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;

        StringBuilder result = new StringBuilder();

        if (years > 0) {
            result.append(years)
                  .append(years > 1 ? " years" : " year")
                  .append(", ");
        }
        if (days > 0) {
            result.append(days)
                  .append(days > 1 ? " days" : " day")
                  .append(", ");
        }
        if (hours > 0) {
            result.append(hours)
                  .append(hours > 1 ? " hours" : " hour")
                  .append(", ");
        }
        if (minutes > 0) {
            result.append(minutes)
                  .append(minutes > 1 ? " minutes" : " minute")
                  .append(", ");
        }
        if (seconds > 0) {
            result.append(seconds)
                  .append(seconds > 1 ? " seconds" : " second")
                  .append(", ");
        }

        result.delete(result.length() - 2, result.length());
        if (result.lastIndexOf(",") != -1) {
            result.replace(result.lastIndexOf(","), result.lastIndexOf(",") + 1, " and");

        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("3 years, 85 days, 1 hour, 9 minutes and 26 seconds".equals(formatDuration(101956166)));
        System.out.println(("1 year, 19 days, 18 hours, 19 minutes and 46 seconds".equals(formatDuration(33243586))));

        System.out.println("3 years, 85 days, 1 hour, 9 minutes and 26 seconds".equals(formatDurationUpdated(101956166)));
        System.out.println(("1 year, 19 days, 18 hours, 19 minutes and 46 seconds".equals(formatDurationUpdated(33243586))));
    }
}
