package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;

import java.io.File;

public class Document implements Attachable, Printable {
    private File document;
    private String title;
    private int size;
    private String ext;

    public Document() {
    }

    public Document setDocument(File document) {
        this.document = document;
        return this;
    }

    public Document setDocument(String documentPath) {
        this.document = new File(documentPath);
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
        return new InputFile(document);
    }

    @Override
    public InputMedia getInputMedia() {
        return new InputMediaDocument(document.getPath());
    }

    //TODO ADD JAVADOC
    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDDCE [%s] %s %s", ext.toUpperCase(), title, Tools.getFormattedSize(size));
    }
}
