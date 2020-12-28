package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Post implements Sendable {

    private String text;

    public Post(String text) {
        this.text = text;
    }

    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {

    }
}
