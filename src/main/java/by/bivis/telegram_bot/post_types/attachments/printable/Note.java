package by.bivis.telegram_bot.post_types.attachments.printable;

public class Note implements Printable {
    private String title;
    private String text;

    public Note() {
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public Note setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDCDD [Заметка] %s\n%s", title, text);
    }
}
