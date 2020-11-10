package by.bivis.vkParser.posts.attachments;

public class Photo extends Attachment {
    private final int id;
    private final long date;
    private final int height;
    private final int width;
    private final String url;

    public Photo(String type, int id, long date, int height, int width, String url) {
        super(type);
        this.id = id;
        this.date = date;
        this.height = height;
        this.width = width;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getUrl() {
        return url;
    }
}
