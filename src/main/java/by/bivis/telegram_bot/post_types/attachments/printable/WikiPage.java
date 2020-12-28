package by.bivis.telegram_bot.post_types.attachments.printable;

public class WikiPage implements Printable{
    private final String title;

    public WikiPage(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return String.format("\uD83D\uDCC3 [Вики страница] %s", title);
    }
}
