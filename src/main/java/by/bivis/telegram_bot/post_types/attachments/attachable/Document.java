package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Document extends Attachment implements Attachable {

    private final InputMediaDocument document;
    private final String title;
    private final int size;
    private final String ext;

    public Document(InputMediaDocument document, String title, int size, String ext) {
        this.document = document;
        this.title = title;
        this.size = size;
        this.ext = ext;
    }

    public Document(String documentPath, String title, int size, String ext) {
        this.document = new InputMediaDocument(documentPath);
        this.title = title;
        this.size = size;
        this.ext = ext;
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
    private String getFormattedSize(int size) {

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

    @Override
    public InputMedia getMedia() {
        return document;
    }

    @Override
    public boolean hasText() {
        return true;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        return String.format("[%s] %s %s", ext.toUpperCase(), title, getFormattedSize(size));
    }
}
