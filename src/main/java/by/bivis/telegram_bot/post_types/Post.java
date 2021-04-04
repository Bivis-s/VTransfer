package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramBot;
import by.bivis.telegram_bot.TelegramChat;
import lombok.Builder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Builder
public class Post implements Sendable {
    private String text;
    private ReplyKeyboard replyKeyboard;

    /**
     * Executes SendMessage in all {@param chats} Telegram chats
     * Usually used for send service notifications
     *
     * @param chats objects of TelegramChat
     * @throws TelegramApiException exception
     */
    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {
        for (TelegramChat chat : chats) {
            TelegramBot bot = chat.getBot();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chat.getChatId());
            sendMessage.setText(text);
            if (replyKeyboard != null) {
                sendMessage.setReplyMarkup(replyKeyboard);
            }
            bot.execute(sendMessage);
        }
    }
}
