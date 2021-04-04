package by.bivis.telegram_bot;

import by.bivis.telegram_bot.db_connector.ChatTableDB;

public class TelegramChat implements DBSerializable {
    private final String chatId;
    private final TelegramBot bot;
    private String condition;
    private ChatTableDB chatTableDB;

    public TelegramChat(String chatId, TelegramBot telegramBot) {
        super();
        this.chatId = chatId;
        this.bot = telegramBot;
        chatTableDB = new ChatTableDB(bot.dbConnector);
    }

    public String getChatId() {
        return chatId;
    }

    public TelegramBot getBot() {
        return bot;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void deleteFromDB() {
        //TODO make removing
    }

    @Override
    public DBSerializable updateDB() {
        chatTableDB.update(this);
        return this;
    }

    @Override
    public DBSerializable updateFromDB() {
        chatTableDB.get();
        return null;
    }
}
