package by.bivis.vkParser.posts;

import by.bivis.vkParser.posts.attachments.Attachment;

import java.util.List;

public class AttachmentPost extends Post {
    private final List<Attachment> attachments;

    public AttachmentPost(int id, int fromId, int ownerId, long date, String postType, String text, int markedAsAds, int isPinned, List<Attachment> attachments) {
        super(id, fromId, ownerId, date, postType, text, markedAsAds, isPinned);
        this.attachments = attachments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }
}
