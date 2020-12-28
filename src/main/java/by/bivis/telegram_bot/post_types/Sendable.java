package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Sendable {

    /**
     * Takes chat(s), sends post to all chats using bot
     * @param chats objects of TelegramChat
     */
    void send(TelegramChat... chats) throws TelegramApiException;

}
