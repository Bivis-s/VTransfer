package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * There may be many Attachable Attachment
 * (Printable Attachment also may be many)
 */
public class MediaGroupPost extends TextPost implements Sendable{

    private boolean isReply;
    private List<Attachment> attachments;

    public MediaGroupPost(int id, int ownerId, int fromId, int date, String text, List<Attachment> attachments) {
        super(id, ownerId, fromId, date, text);
        this.attachments = attachments;

        isReply = false;
    }

    public MediaGroupPost(int id, int ownerId, int fromId, int date, String text, int replyOwnerId, int replyPostId, List<Attachment> attachments) {
        super(id, ownerId, fromId, date, text, replyOwnerId, replyPostId);
        this.attachments = attachments;

        isReply = true;
    }

    @Override
    public void send(TelegramChat... chats) {

    }

}
