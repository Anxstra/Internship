package five;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RgbToHex {

    public static String rgb(int r, int g, int b) {
        if (r < 0 || r > 255) {
            r -= r % 255;
        }
        if (g < 0 || g > 255) {
            g -= g % 255;
        }
        if (b < 0 || b > 255) {
            b -= b % 255;
        }
        return String.format("%02X%02X%02X", r, g, b);
    }

    public static String rgbWithStream(int r, int g, int b) {
        return Stream.of(r, g, b)
                     .map(i -> i < 0 || i > 255 ? i - i % 255 : i)
                     .map(i -> String.format("%02X", i))
                     .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println("FFFFFF".equals(rgb(255, 255, 255)));
        System.out.println("00FF7D".equals(rgb(-20, 275, 125)));
        System.out.println("FFFFFF".equals(rgbWithStream(255, 255, 255)));
        System.out.println("00FF7D".equals(rgbWithStream(-20, 275, 125)));
        System.out.println("00FF7D".equals(rgbWithStream(-20, 275, 125)));
    }
}
