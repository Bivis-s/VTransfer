package by.bivis.telegram_bot.post_types.attachments.printable;

import by.bivis.telegram_bot.post_types.attachments.Attachment;

public class Note extends Attachment implements Printable {
    private final String title;
    private final String noteText;

    public Note(String title, String noteText) {
        this.title = title;
        this.noteText = noteText;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return String.format("\uD83D\uDCDD [Заметка] %s\n%s", title, noteText);
    }
}
