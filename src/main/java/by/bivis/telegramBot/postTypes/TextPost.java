package by.bivis.telegramBot.postTypes;

import by.bivis.telegramBot.TelegramBot;
import by.bivis.telegramBot.TelegramChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextPost implements Sendable {

    private int id;
    private int ownerId;
    private int fromId;
    private int date;
    private String text;
    private int replyOwnerId;
    private int replyPostId;
    private boolean isReply;

    public TextPost(int id, int ownerId, int fromId, int date, String text) {
        this.id = id;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.date = date;
        this.text = text;

        this.isReply = false;
    }

    public TextPost(int id, int ownerId, int fromId, int date, String text, int replyOwnerId, int replyPostId) {
        this.id = id;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.date = date;
        this.text = text;
        this.replyOwnerId = replyOwnerId;
        this.replyPostId = replyPostId;

        this.isReply = true;
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
                        "\n" +
                        text +
                        "\n" + "-----" + "\n" +
                        bot.dbConnector.getVKSourceLinkById(321));

                bot.execute(toSend);
            }
        } else {
            //TODO Implement sending reply-Posts
        }
    }
}
