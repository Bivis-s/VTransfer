package by.bivis.vkParser.posts.attachments;

public class Video extends Attachment{
    private final int id;
    private final int ownerId;
    private final long date;
    private final String title;
    private final String description;

    public Video(String type, int id, int owner_id, long date, String title, String description) {
        super(type);
        this.id = id;
        this.ownerId = owner_id;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
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

    public String getUrl() {
        return "https://vk.com/video" + ownerId + "_" + id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", owner_id=" + ownerId +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + getUrl() + '\'' +
                "} " + super.toString();
    }
}
