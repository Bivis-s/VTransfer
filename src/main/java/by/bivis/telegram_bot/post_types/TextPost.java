package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * There must be no Attachable Attachment
 * (Printable Attachment may be many)
 */
public class TextPost implements Sendable {
    protected String text;
    protected Sendable replyPost;
    protected InlineKeyboardMarkup inlineKeyboardMarkup;
    protected List<Printable> printableAttachmentList;

    public TextPost() {
    }

    public TextPost setText(String text) {
        this.text = text;
        return this;
    }

    public TextPost setReplyPost(Sendable replyPost) {
        this.replyPost = replyPost;
        return this;
    }

    public TextPost setInlineKeyboardMarkup(InlineKeyboardMarkup inlineKeyboardMarkup) {
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
        return this;
    }

    public TextPost setPrintableAttachmentList(List<Printable> printableAttachmentList) {
        this.printableAttachmentList = printableAttachmentList;
        return this;
    }

    protected String formatPrintableAttachments() {
        if (printableAttachmentList != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Printable printable : printableAttachmentList) {
                stringBuilder.append(printable.getFormattedText()).append("\n");
            }
            return "\n\n" + stringBuilder.toString();
        } else {
            return "";
        }
    }

    protected String getSeparatedText() {
        return "\n" + text + "\n";
    }

    public boolean isReply() {
        return replyPost != null;
    }

    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {
        if (!isReply()) {
            for (TelegramChat chat : chats) {
                SendMessage toSend = new SendMessage();
                toSend.setChatId(chat.getChatId());
                toSend.setText(getSeparatedText() + formatPrintableAttachments());
                if (inlineKeyboardMarkup != null) {
                    toSend.setReplyMarkup(inlineKeyboardMarkup);
                }
                (chat.getBot()).execute(toSend);
            }
        } else if (isReply()) {
            //TODO Implement sending reply-Posts
        }
    }
}
