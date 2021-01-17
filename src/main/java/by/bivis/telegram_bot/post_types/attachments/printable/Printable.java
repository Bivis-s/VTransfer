package by.bivis.telegram_bot.post_types.attachments.printable;

/**
 * It means that Attachment can't (or unnecessarily) be attached to Message directly and its content shall be built in Message's text
 */
public interface Printable {
    String getFormattedText();
}
