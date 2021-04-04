package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * There must be no Attachable Attachment
 * (Printable Attachment may be many)
 */
@SuperBuilder
@AllArgsConstructor
public class TextPost implements Sendable {
    protected String sourceName;
    protected String text;
    protected int replyPostId;
    protected InlineKeyboardMarkup inlineKeyboardMarkup;
    protected List<Printable> printableAttachmentList;

    protected String getFormattedSourceName() {
        return "<b>" + sourceName + "</b>\n";
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

    private String escapeSpecChars(String text) {
        return text
                .replaceAll("\\*", "\\*")
                .replaceAll("_", "\\_")
                .replaceAll("`", "\\`")
                .replaceAll("\\[", "\\[");
    }

    protected String getFormattedText() {
        return "\n" + text + "\n";
    }

    public boolean isReply() {
        return replyPostId != 0;
    }

    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {
        if (!isReply()) {
            for (TelegramChat chat : chats) {
                SendMessage toSend = new SendMessage();
                toSend.setChatId(chat.getChatId());
                toSend.enableMarkdownV2(true);
                toSend.enableHtml(true);
                toSend.setText(escapeSpecChars(getFormattedSourceName() + getFormattedText() + formatPrintableAttachments()));
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
