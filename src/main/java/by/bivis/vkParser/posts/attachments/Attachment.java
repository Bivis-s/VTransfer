package by.bivis.vkParser.posts.attachments;

public abstract class Attachment {
    private final String type;

    public Attachment(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String getUrl();

    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                '}';
    }
}
