package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * There must be only 1 Attachable Attachment
 * (Printable Attachment may be many)
 */
public class MediaPost extends TextPost implements Sendable{

    private boolean isReply = false;
    private Attachment attachment;

    public MediaPost(int id, int ownerId, int fromId, int date, String text, Attachment attachment) {
        super(id, ownerId, fromId, date, text);
        this.attachment = attachment;
    }

    public MediaPost(int id, int ownerId, int fromId, int date, String text, int replyOwnerId, int replyPostId, Attachment attachment) {
        super(id, ownerId, fromId, date, text, replyOwnerId, replyPostId);
        this.attachment = attachment;

        isReply = true;
    }

    @Override
    public void send(TelegramChat... chats) {

    }
}
