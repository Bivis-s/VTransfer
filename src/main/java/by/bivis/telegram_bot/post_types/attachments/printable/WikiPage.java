package by.bivis.telegram_bot.post_types.attachments.printable;

public class WikiPage implements Printable{
    private String title;

    public WikiPage() {
    }

    public WikiPage setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDCC3 [Вики страница] %s", title);
    }
}
