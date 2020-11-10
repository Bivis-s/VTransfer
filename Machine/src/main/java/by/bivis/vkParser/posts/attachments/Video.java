package by.bivis.vkParser.posts.attachments;

public class Video extends Attachment{
    private final int id;
    private final int owner_id;
    private final long date;
    private final String title;
    private final String description;

    public Video(String type, int id, int owner_id, long date, String title, String description) {
        super(type);
        this.id = id;
        this.owner_id = owner_id;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return "https://vk.com/video" + owner_id + "_" + id;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public long getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
