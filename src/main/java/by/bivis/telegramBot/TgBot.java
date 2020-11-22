package by.bivis.telegramBot;

import by.bivis.database.Manipulator;
import by.bivis.settings.Settings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import by.bivis.telegramBot.users.User;
import by.bivis.vkParser.JSONs.GroupSearch;
import by.bivis.vkParser.JSONs.JSONParser;
import by.bivis.vkParser.JSONs.tools.FormatText;
import by.bivis.vkParser.posts.Post;
import by.bivis.vkParser.posts.attachments.Attachment;
import by.bivis.vkParser.sources.Source;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBot extends TelegramLongPollingBot {
    private final String TOKEN = Settings.getSetting("tgToken");
    private final String BOT_USERNAME = Settings.getSetting("tgBotUsername");
    Manipulator manipulator = new Manipulator();
    GroupSearch groupSearch = new GroupSearch();

    public TgBot() throws IOException, SQLException {
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

        if (textFromMessage.equals("/start")) {
            user.setCondition("start");
            try {
                manipulator.resetUser(user);
                sendMsg(update.getMessage(), "Я рад, что мы встретились! \nДавай подпишем тебя на парочку групп. \nНажми кнопку /add чтобы сделать это", false, new String[]{});
                sendMsg(update.getMessage(), Settings.infoText, false, new String[]{"/add", "/info"});
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (textFromMessage.equals("/stop")) {
            try {
                user.setCondition("stop");
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
                    sendMsg(update.getMessage(), "Продолжаю работу\n------\n" + subscriptions, false, new String[]{});
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
        } else if (textFromMessage.equals("/text")) {
            sendMsg(update.getMessage(), "Бог умер! Бог не_воскреснет! И мы его убили! Как утешимся мы, убийцы из убийц! Самое святое и могущественное Существо, какое только было в мире, истекло кровью под нашими ножами — кто смоет с нас эту кровь?"
                    , false, new String[]{});
        } else if (textFromMessage.equals("/delete")) {
            String subscriptions = null;
            try {
                subscriptions = getSubscriptionsList(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (subscriptions == null) {
                sendMsg(update.getMessage(), "Список подписок пуст, давай это исправим?\n/start", false, new String[]{"/start"});
            } else {
                user.setCondition("delete");
                try {
                    manipulator.updateCondition(user);
                    sendMsg(update.getMessage(), getSubscriptionsListForDelete(user) + "\n-----" + "\nУдалить всё: /delete_all", false, new String[]{"/add"});
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } else if (textFromMessage.equals("/killme")) {
            try { //TODO полный ресет этой командой, пока только удаляет пользователя, но не удаляет его подписки
                manipulator.deleteUser(user);

                sendMsg(update.getMessage(), "Прощай... \uD83D\uDE25", false, new String[]{});
                System.out.println("Пользователь успешно удалён!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (textFromMessage.equals("/info")) {
            sendMsg(update.getMessage(), Settings.infoText, false, new String[]{"/continue"});
        } else {
            // Режим добавления
            try {
                if ((textFromMessage.charAt(0) != '/') && (manipulator.getCondition(user).equals("add"))) {

                    //Определяем что нам передано: название группы для поиска или ссылка и соответсвующим методом делаем объект источника
                    Source source;
                    if (textFromMessage.contains("vk.com/")) {
                        //TODO заменить бы эту хуйню на нормальный regex
                        String screen_name = textFromMessage.replaceFirst("https://", "").replaceFirst("vk\\.com/", "");
                        source = groupSearch.makeSourceFromScreenName(screen_name);
                    } else {
                        source = groupSearch.makeSourceObjectFromSearch(textFromMessage);
                    }

                    if (manipulator.thereIsConnection(user, source)) {
                        sendMsg(update.getMessage(), "Вы уже подписаны на " + source.getName() + "\nhttps://vk.com/" + source.getScreen_name(), true, new String[]{"/add", "/continue"});
                    } else {
                        if (!manipulator.thereIsSource(source)) {
                            manipulator.addSource(source);
                        }
                        manipulator.addConnection(user, source);
                        sendPhoto(update.getMessage(), "Группа " + source.getName() + " успешно добавлена!" + "\nhttps://vk.com/" + source.getScreen_name() + "\nВведите название следующий группы или команду /continue", source.getPhotoURL(), true, new String[]{"/add", "/continue"});
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendMsg(update.getMessage(), "Группа не найдёна", true, new String[]{"/add", "/continue"});
            }
            try {
                if ((textFromMessage.contains("/delete_")) && manipulator.getCondition(user).equals("delete")) {
                    String textAfterCommand = textFromMessage.replace("/delete_", "");

                    if (textAfterCommand.equals("all")) {
                        manipulator.removeAllConnections(user);
                        sendMsg(update.getMessage(), "Все ваши подписки очищены \n(Но тут всё ещё плохо пахнет \uD83E\uDD22)", true, new String[]{"/add", "/continue"});
                    }
                    int sourceId = Integer.parseInt(textAfterCommand);
                    Source source = manipulator.getSourceById(sourceId);
                    if (manipulator.thereIsConnection(user, source)) {
                        manipulator.removeConnection(user, source);
                        sendMsg(update.getMessage(), "Группа " + source.getName() + " успешно удалена!", true, new String[]{"/delete", "/continue"});
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                sendMsg(update.getMessage(), "Вы не подписаны на эту группу", true, new String[]{"/delete", "/continue"});
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
        sendMessage.setText(FormatText.format(text).replaceAll("_", "\\\\_"));

        try {
            if (keyboardArgs != null) {
                setKeyboard(sendMessage, keyboardArgs); // add button row under text field after sending message
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendHTML(Message message, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode("HTML");
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        execute(sendMessage);
    }

    public synchronized void sendPhoto(Message message, String text, String URL, boolean itsReply, String[] keyboardArgs) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        if (itsReply) {
            sendPhoto.setReplyToMessageId(message.getMessageId());
        }
        //replace _ char to \\_ for escaping exception ...why?
        sendPhoto.setCaption(text.replaceAll("_", "\\_"));
        InputFile inputFile = new InputFile();
        inputFile.setMedia(URL);
        sendPhoto.setPhoto(inputFile);

        execute(sendPhoto);
    }

    public String getSubscriptionsList(User user) throws SQLException {
        List<Source> sourceList = manipulator.getSubscriptionsList(user);
        if (sourceList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Source item : sourceList) {
                stringBuilder.append(item.getName()).append("\n");
            }
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public String getSubscriptionsListForDelete(User user) throws SQLException {
        List<Source> sourceList = manipulator.getSubscriptionsList(user);
        if (sourceList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Source item : sourceList) {
                stringBuilder.append(item.getName()).append(" /delete_").append(item.getId()).append("\n");
            }
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private void sendTextMessage(Post post, int userId) throws SQLException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(userId));
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode("HTML");
        sendMessage.setDisableWebPagePreview(true);
        String text = "<b>" + manipulator.getSourceById(post.getOwnerId() * -1).getName() + "</b>\n " + post.getText() + "\n-----\n"
                + post.getLink();
        try {
            sendMessage.setText(FormatText.format(text));
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(post.getText());
            System.out.println(post.toString());
        }
    }

    private void sendPost(Post post, int userId) throws SQLException, TelegramApiException {
        // если это простой пост без вложений
        if (post.getAttachments() != null) {

            List<Attachment> attachments = post.getAttachments();
            // если в посте одно вложение (фото или видео)
            if (attachments.size() == 1) {
                String type = attachments.get(0).getType();
                InputFile inputFile = new InputFile(attachments.get(0).getUrl());
                if (type.equals("photo")) {
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(String.valueOf(userId));
                    sendPhoto.setParseMode("HTML");
                    sendPhoto.setPhoto(inputFile);
                    System.out.println(post.getOwnerId());
                    String text = "<b>" + manipulator.getSourceById(post.getOwnerId() * -1).getName() + "</b>\n" + post.getText() + "\n-----\n"
                            + post.getLink();
                    sendPhoto.setCaption(FormatText.format(text));
                    execute(sendPhoto);

                } else if (type.equals("video")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(userId));
                    sendMessage.setParseMode("HTML");
                    String text = "<b>" + manipulator.getSourceById(post.getOwnerId() * -1).getName() + "</b>\n" + post.getText() + "\n "
                            + attachments.get(0).getUrl() + "\n " + post.getLink();
                    sendMessage.setText(FormatText.format(text));
                    try {
                        execute(sendMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(inputFile.toString());
                    }

                } else if (type.equals("nonprint")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(userId));
                    sendMessage.enableMarkdown(true);
                    sendMessage.setParseMode("HTML");
                    sendMessage.setText("</b>" + manipulator.getSourceById(post.getOwnerId() * -1).getName() + "</b>\n "
                            + FormatText.format(post.getText()) + "\n "
                            + post.getLink() + "\n\n " + "__Пост имеет невыводимое вложение__");
                    execute(sendMessage);
                }
            }
        }
    }

    // if printable attachments > 1
    private void sendAttachmentsPostToSend(Post post, int userId) throws TelegramApiException {

        List<Attachment> attachments = post.getAttachments();
        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        sendMediaGroup.setChatId(String.valueOf(userId));
        List<InputMedia> inputMediaList = new ArrayList<>();
        for (Attachment attachment : attachments) {
            String type = attachment.getType();
            if (type.equals("photo")) {
                InputMedia inputMedia = new InputMediaPhoto(attachment.getUrl());
                inputMediaList.add(inputMedia);
            } else if (type.equals("video")) {
                InputMedia inputMedia = new InputMediaVideo(attachment.getUrl());
                inputMediaList.add(inputMedia);
            }
        }
        sendMediaGroup.setMedias(inputMediaList);

        execute(sendMediaGroup);
    }

    private void sendOut(List<Integer> userIds, Post post) throws TelegramApiException, SQLException {
        for (int id : userIds) {
            List<Attachment> attachments = post.getAttachments();
            if (attachments != null) {
                if (attachments.size() == 1) {
                    sendPost(post, id);
                } else if (post.getPrintableAttachmentsQuantity() > 1) {
                    sendAttachmentsPostToSend(post, id);
                }
            } else {
                sendTextMessage(post, id);
            }
        }
    }

    public void startSending() throws Exception {
        int count = 5;

        while (true) {
            List<Integer> connectedSourceIds = manipulator.getConnectedSourceIds();
            for (int ownerId:
                 connectedSourceIds) {
                List<Integer> connectedUserIds = manipulator.getConnectedUserIds(ownerId);
                List<Post> posts = JSONParser.makePostObjectsList(ownerId, count);
                for (Post post : posts) {
                    if (!manipulator.thereIsPost(post)) {
                        manipulator.addPost(post);
                        try {
                            sendOut(connectedUserIds, post);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(200);
                    }
                }
                Thread.sleep(20000);
            }
        }
    }
}
