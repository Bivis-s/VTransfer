package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramBot;
import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * There must be no Attachable Attachment
 * (Printable Attachment may be many)
 */
public class TextPost extends Post implements Sendable {

    private int id;
    private int ownerId;
    private int fromId;
    private int date;
    private String text;
    private int replyOwnerId;
    private int replyPostId;
    private boolean isReply;
    private List<Printable> printableAttachmentsList;

    public TextPost(int id, int ownerId, int fromId, int date, String text) {
        super(text);
        this.id = id;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.date = date;

        this.isReply = false;
    }

    public TextPost(int id, int ownerId, int fromId, int date, String text, int replyOwnerId, int replyPostId) {
        super(text);
        this.id = id;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.date = date;
        this.replyOwnerId = replyOwnerId;
        this.replyPostId = replyPostId;

        this.isReply = true;
    }

    protected String formatPrintableAttachments() {
        if (printableAttachmentsList != null) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Printable printable : printableAttachmentsList) {
                stringBuilder.append(printable.getText());
            }
            return "\n\n" + stringBuilder.toString();
        } else {
            return "";
        }
    }

    //TODO Напиисать метод для добавления кнопок-ссылок под пост (ссылка на пост)
    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {
        if (!isReply) {
            for (TelegramChat chat : chats) {

                TelegramBot bot = chat.getBot();
                SendMessage toSend = new SendMessage();
                toSend.setChatId(String.valueOf(chat.getChatId()));

                toSend.setText(bot.dbConnector.getVKSourceNameById(123) +
                        "\n" + text +
                        formatPrintableAttachments() +
                        "\n" + "-----" + "\n" +
                        bot.dbConnector.getVKSourceLinkById(321));
                        //TODO заменить две верхние строки на кнопку под постом

                bot.execute(toSend);
            }
        } else {
            //TODO Implement sending reply-Posts
        }
    }
}
