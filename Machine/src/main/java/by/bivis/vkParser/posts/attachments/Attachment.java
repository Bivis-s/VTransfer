package by.bivis.vkParser.posts.attachments;


//TODO Реализовать интерфейс работы с вложениями
public abstract class Attachment {
    private final String type;

    public Attachment(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
