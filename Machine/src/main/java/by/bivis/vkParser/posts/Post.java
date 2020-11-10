package by.bivis.vkParser.posts;

public class Post {
    private final int id; // post id (in url)
    private final int fromId; // id who posted
    private final int ownerId; // wall's owner id (in url)
    private final long date;
    private final String postType;
    private final String text;
    private final int markedAsAds;
    private final int isPinned;

    public Post(int id, int fromId, int ownerId, long date, String postType, String text, int markedAsAds, int isPinned) {
        this.id = id;
        this.fromId = fromId;
        this.ownerId = ownerId;
        this.date = date;
        this.postType = postType;
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

    public String getPostType() {
        return postType;
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
}
