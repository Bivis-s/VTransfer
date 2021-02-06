package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;


public class Document implements Attachable, Printable {
    private String documentPath;
    private String title;
    private int size;
    private String ext;

    public Document() {
    }

    public Document setDocument(String documentPath) {
        this.documentPath = documentPath;
        return this;
    }

    public Document setTitle(String title) {
        this.title = title;
        return this;
    }

    public Document setSize(int size) {
        this.size = size;
        return this;
    }

    public Document setExt(String ext) {
        this.ext = ext;
        return this;
    }

    @Override
    public InputFile getInputFile() {
        return new InputFile(documentPath);
    }

    @Override
    public InputMediaDocument getInputMedia() {
        return new InputMediaDocument(documentPath);
    }

    //TODO ADD JAVADOC
    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDDCE [%s] %s %s", ext.toUpperCase(), title, Tools.getFormattedSize(size));
    }
}
