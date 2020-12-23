package by.bivis.telegramBot;

import by.bivis.telegramBot.postTypes.TextPost;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    public final DBConnector dbConnector;

    public TelegramBot(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public String getBotUsername() {
        return Values.TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Values.TELEGRAM_BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        TelegramChat chat = new TelegramChat(update.getMessage().getChatId().toString(), this);

        sendTestTextPost(chat);

    }

    private void sendTestTextPost(TelegramChat chat) {
        TextPost textPost = new TextPost(5156, 56782, 34125, 9988998, "If there is more than 1 photo, bot can send it using only SendMediaGroup. It means that sending more than 1 photo needs to send 2 messages: with photos and with text");

        try {
            textPost.send(chat);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
