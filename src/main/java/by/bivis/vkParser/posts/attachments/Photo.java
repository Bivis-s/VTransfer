package by.bivis.vkParser.posts.attachments;

public class Photo extends Attachment {
    private final int id;
    private final long date;
    private final String url;

    public Photo(String type, int id, long date, String url) {
        super(type);
        this.id = id;
        this.date = date;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", date=" + date +
                ", url='" + url + '\'' +
                "} " + super.toString();
    }
}
