package by.bivis.telegramBot;

import by.bivis.settings.Settings;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBot extends TelegramLongPollingBot {
    private final String TOKEN = Settings.getSetting("tgToken");
    private final String BOTUSERNAME = Settings.getSetting("tgBotUsername");

    public TgBot() throws IOException {
    }


    public String getBotUsername() {
        return BOTUSERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            try {
                execute(new SendMessage(chatId, "Пошёл нахуй, " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }


}
