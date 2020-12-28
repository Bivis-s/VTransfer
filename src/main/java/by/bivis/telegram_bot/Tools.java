package by.bivis.telegram_bot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tools {

    //TODO ADD JAVADOC
    public static String getFormattedDuration(int duration) {
        int hours = (duration / 3600) % 24;
        int minutes = (duration / 60) % 60;
        int seconds = duration % 60;

        if (hours > 0) {
            return(String.format("%02d", hours) + ":" +
                    String.format("%02d", minutes) + ":" +
                    String.format("%02d", seconds));
        } else {
            return String.format("%02d", minutes) + ":" +
                    String.format("%02d", seconds);
        }
    }

    /**
     * Formats int number to String like "2.15GB" or "15.76KB"
     *
     * Only suitable for files smaller than 2147483647 bytes
     * The part of the number before the dot always will be greater than 1
     * The part of the number after the dot always will have 2 digit, except size < 1000
     * Number always rounds up
     * Available units: B, KB, MB, GB
     *
     * @param size int count of bytes
     * @return String containing a number and a unit
     */
    public static String getFormattedSize(int size) {

        String unit = "B";
        double dividedSize = size;

        if (size < 1000) {
            return dividedSize + unit;
        } else if (size < Math.pow(10, 6)) {
            dividedSize = (double) size / 1000;
            unit = "KB";
        } else if (size >= Math.pow(10, 6) && size < Math.pow(10, 9)) {
            dividedSize = (double) size / Math.pow(10, 6);
            unit = "MB";
        } else if (size >= Math.pow(10, 9)) {
            dividedSize = (double) size / Math.pow(10, 9);
            unit = "GB";
        }

        return ((new BigDecimal(dividedSize)).setScale(2, RoundingMode.HALF_UP)) + unit;
    }
}
