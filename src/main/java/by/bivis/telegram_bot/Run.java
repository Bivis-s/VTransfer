package by.bivis.telegram_bot;

import by.bivis.telegram_bot.db_connector.DBConnector;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Run {
    public static DBConnector dbConnector;
    public static TelegramBot telegramBot;
    public static TelegramBotsApi telegramBotsApi;

    public static void main(String... args) throws Exception {
        dbConnector = new DBConnector();
        telegramBot = new TelegramBot(dbConnector);
        telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
