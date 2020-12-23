package by.bivis.telegramBot;


public class TelegramChat {

    private final String chatId;
    private final TelegramBot bot;

    public TelegramChat(String chatId, TelegramBot telegramBot) {
        super();
        this.chatId = chatId;
        this.bot = telegramBot;
    }

    public String getChatId() {
        return chatId;
    }

    public TelegramBot getBot() {
        return bot;
    }

}
