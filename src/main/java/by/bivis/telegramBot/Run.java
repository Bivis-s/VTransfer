package by.bivis.telegramBot;

import by.bivis.vkParser.JSONs.JSONParser;
import by.bivis.vkParser.posts.Post;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Run {

    public static void main(String[] args) throws Exception {
        TgBot tgBot = new TgBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        tgBot.startSending();
//        tgBot.sendSpecChars();
    }
}

// TODO не добавляет по сслыке сообщества с адресами типа https://vk.com/public200525909
