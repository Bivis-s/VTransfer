package by.bivis.telegram_bot;

import by.bivis.telegram_bot.db_connector.DBConnector;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        UpdateReceiver.handleUpdate(update, chat);
    }

    private void sendTextPostTest(TelegramChat chat) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());
        sendMessage.setText("u gay?");
        sendMessage.setReplyMarkup(KeyboardGenerator.createInlineKeyboardMarkup(2, "home", "info", "help", "start", "close"));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
