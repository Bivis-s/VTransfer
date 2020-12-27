package by.bivis.telegram_bot;

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
}
