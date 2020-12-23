package by.bivis.telegramBot.postTypes;

import by.bivis.telegramBot.TelegramBot;
import by.bivis.telegramBot.TelegramChat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Sendable {

    /**
     * Takes bot and chat(s), sends post to all chats using bot
     * @param chats objects of TelegramChat
     */
    void send(TelegramChat... chats) throws TelegramApiException;

}
