package by.bivis.telegramBot;

import by.bivis.database.Manipulator;
import by.bivis.database.Subscriptions;
import by.bivis.settings.Settings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import by.bivis.telegramBot.users.User;
import by.bivis.vkParser.JSONs.GroupSearch;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBot extends TelegramLongPollingBot {
    private final String TOKEN = Settings.getSetting("tgToken");
    private final String BOT_USERNAME = Settings.getSetting("tgBotUsername");
    Manipulator manipulator = new Manipulator();
    GroupSearch groupSearch = new GroupSearch();

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
        Message message = update.getMessage();
        User user = new User(message.getFrom(), "none");
        String textFromMessage = update.getMessage().getText();

//        // set user condition from database
//        try {
//            if (manipulator.thereIsUser(user)) {
//                try {
//                    user.setCondition(manipulator.getCondition(user));
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        if (textFromMessage.equals("/start")) {
            user.setCondition("start");
            try {
                manipulator.resetUser(user);
                sendMsg(update.getMessage(), "Я рад, что мы встретились! \nДавай подпишем тебя на парочку групп. \nНажми кнопку /add чтобы сделать это", false, new String[]{"/add", "/info"});
                System.out.println("Пользователь успешно обновлён!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            sendMsg(update.getMessage(), "https://sun9-48.userapi.com/WjSsrzNh3mVps_ISx4NKAxkd7UQQIPDty4-9eQ/wiM8w5MHf2U.jpg", false, new String[]{"/add", "/start", "/info"});
//            System.out.println(update.getMessage().getReplyToMessage().getText());

        } else if (textFromMessage.equals("/stop")) {
            user.setCondition("stop");
            try {
                manipulator.updateCondition(user);
                sendMsg(update.getMessage(), "Моя остановочка \uD83C\uDFA9", false, new String[]{"/continue"});
                System.out.println("Пользователь успешно удалён!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (textFromMessage.equals("/continue")) {
            try {
                String subscriptions = getSubscriptionsList(user);

                if (subscriptions == null) {
                    sendMsg(update.getMessage(), "Список подписок пуст, давай это исправим? \n/start", false, new String[]{"/start"});
                } else {
                    user.setCondition("continue");
                    manipulator.updateCondition(user);
                    sendMsg(update.getMessage(), "Список подписок: \n" + subscriptions, false, new String[]{"/add"});
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (textFromMessage.equals("/add")) {
            try {
                user.setCondition("add");
                manipulator.updateCondition(user);
                sendMsg(update.getMessage(), "Введи название группы", false, new String[]{"/add", "/continue"});
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (textFromMessage.equals("/killme")) {
            try {
                manipulator.deleteUser(user);
                sendMsg(update.getMessage(), "Прощай... \uD83D\uDE25", false, new String[]{});
                System.out.println("Пользователь успешно удалён!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                if ((textFromMessage.charAt(0) != '/') && (manipulator.getCondition(user).equals("add"))) {
                    sendMsg(update.getMessage(), "типа ищу группу ага", false, new String[]{});
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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

    public String getSubscriptionsList(User user) throws SQLException {
        List<Subscriptions> subscriptionsList = manipulator.getSubscriptionsList(user);
        if (subscriptionsList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Subscriptions item : subscriptionsList) {
                stringBuilder.append(manipulator.getSourceName(item));
            }
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

}
