package by.bivis.vkParser.posts.attachments;

public class NonPrintableAttachment extends Attachment{
    public NonPrintableAttachment(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return "NonPrintableAttachment{} " + super.toString();
    }
}
