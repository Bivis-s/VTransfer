package by.bivis.telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException, TelegramApiException {
        TgBot tgBot = new TgBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
