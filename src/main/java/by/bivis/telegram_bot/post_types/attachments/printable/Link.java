package by.bivis.telegram_bot.post_types.attachments.printable;

public class Link implements Printable {
    private String url;
    private String title;

    public Link() {
    }

    public Link setUrl(String url) {
        this.url = url;
        return this;
    }

    public Link setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getFormattedText() {
        return String.format("\uD83C\uDF10 [Ссылка] %s\n%s", title, url);
    }
}
