package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;

public class Document extends Attachment implements Attachable, Printable {
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

    public InputMediaDocument getDocument() {
        return document;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public String getExt() {
        return ext;
    }

    @Override
    public InputMedia getMedia() {
        return document;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        return String.format("\uD83D\uDDCE [%s] %s %s", ext.toUpperCase(), title, Tools.getFormattedSize(size));
    }
}
