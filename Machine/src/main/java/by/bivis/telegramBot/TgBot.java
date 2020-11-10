package by.bivis.telegramBot;

import by.bivis.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBot extends TelegramLongPollingBot {
    private final String TOKEN = Settings.getSetting("tgToken");
    private final String BOT_USERNAME = Settings.getSetting("tgBotUsername");

    public TgBot() throws IOException {
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.getMessage().getText().equals("/start")) {
            sendMsg(update.getMessage(), "https://sun9-48.userapi.com/WjSsrzNh3mVps_ISx4NKAxkd7UQQIPDty4-9eQ/wiM8w5MHf2U.jpg", false, new String[]{"/help", "/settings", "/info"});
        }

    }

    public void setKeyboard(SendMessage sendMessage, String[] buttonsText) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>(); // create keyboard row
        KeyboardRow keyboardRowFirstLine = new KeyboardRow(); // create firs line of keyboard row
        for (String button : buttonsText) {
            keyboardRowFirstLine.add(new KeyboardButton(button)); // add button in first line
        }
        keyboardRowList.add(keyboardRowFirstLine); // add first line to keyboard row
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public synchronized void sendMsg(Message message, String text, boolean itsReply, String[] keyboardArgs) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        if (itsReply) {
            sendMessage.setReplyToMessageId(message.getMessageId());
        }

        //replace _ char to \\_ for escaping exception ...why?
        sendMessage.setText(text.replaceAll("_", "\\\\_"));

        try {
            if (keyboardArgs != null) {
                setKeyboard(sendMessage, keyboardArgs); // add button row under text field after sending message
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
