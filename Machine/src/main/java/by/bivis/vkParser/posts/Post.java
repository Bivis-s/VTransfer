package by.bivis.vkParser.posts;

import by.bivis.database.SQLAble;
import by.bivis.vkParser.posts.attachments.Attachment;

import java.util.ArrayList;
import java.util.List;

public class Post implements SQLAble {
    private final int id; // post id (in url)
    private final int fromId; // id who posted
    private final int ownerId; // wall's owner id (in url)
    private final long date;
    private final String type;
    private final String text;
    private final int markedAsAds;
    private final int isPinned;

    public Post(int id, int fromId, int ownerId, long date, String type, String text, int markedAsAds,
                int isPinned) {
        this.id = id;
        this.fromId = fromId;
        this.ownerId = ownerId;
        this.date = date;
        this.type = type;
        this.text = text;
        this.markedAsAds = markedAsAds;
        this.isPinned = isPinned;
    }

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public long getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getMarkedAsAds() {
        return markedAsAds;
    }

    public int getIsPinned() {
        return isPinned;
    }

    public String getLink() {
        return "https://vk.com/w=wall" + ownerId + "_" + id;
    }

    public List<Attachment> getAttachments() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", ownerId=" + ownerId +
                ", date=" + date +
                ", postType='" + type + '\'' +
                ", text='" + text + '\'' +
                ", markedAsAds=" + markedAsAds +
                ", isPinned=" + isPinned +
                '}';
    }

    @Override
    public String toSQLString() {
        return id + ", " + ownerId + ", " + date;
    }
}
