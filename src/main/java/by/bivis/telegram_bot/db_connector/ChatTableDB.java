package by.bivis.telegram_bot.db_connector;

import by.bivis.telegram_bot.TelegramChat;
import org.sqlite.jdbc4.JDBC4ResultSet;

import java.sql.ResultSet;

public class ChatTableDB extends DBConnector{
    private String condition;
    private final DBConnector DB_CONNECTOR;

    public ChatTableDB(DBConnector dbConnector) {
        this.DB_CONNECTOR = dbConnector;
    }

    public String getChatConditionById(String id) {
        return condition;
    }

    public void setChatConditionById(String id, String condition) {
        this.condition = condition;
    }

    public void update(TelegramChat chat) {

    }

    public ResultSet get() {
        return null;
    }
}
