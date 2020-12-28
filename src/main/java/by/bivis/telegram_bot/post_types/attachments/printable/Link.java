package by.bivis.telegram_bot.post_types.attachments.printable;

import by.bivis.telegram_bot.post_types.attachments.Attachment;

public class Link extends Attachment implements Printable {
    private final String url;
    private final String title;

    public Link(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return String.format("\uD83C\uDF10 [Ссылка] %s\n%s", title, url);
    }
}
