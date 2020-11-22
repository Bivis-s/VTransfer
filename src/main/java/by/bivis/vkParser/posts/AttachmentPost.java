package by.bivis.vkParser.posts;

import by.bivis.vkParser.posts.attachments.Attachment;

import java.util.List;

// i really don't know for what i have this class
public class AttachmentPost extends Post {
    private final List<Attachment> attachments;

    public AttachmentPost(int id, int fromId, int ownerId, long date, String type, String text, int markedAsAds,
                          int isPinned, List<Attachment> attachments) {
        super(id, fromId, ownerId, date, type, text, markedAsAds, isPinned);
        this.attachments = attachments;
    }

    @Override
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public int getPrintableAttachmentsQuantity() {
        int count = 0;
        for (Attachment attachment : attachments) {
            String type = attachment.getType();
            if (type.equals("photo") || type.equals("video")) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "AttachmentPost{" +
                "attachments=" + attachments.toString() +
                "} " + super.toString();
    }
}
