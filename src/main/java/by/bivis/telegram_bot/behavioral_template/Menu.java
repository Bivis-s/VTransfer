package by.bivis.telegram_bot.behavioral_template;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.typical_posts.TypicalPosts;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Menu {
    private final TelegramChat chat;
    private Update update;

    public Menu(TelegramChat chat, Update update) {
        this.chat = chat;
        this.update = update;
    }

    public void start() throws TelegramApiException {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    TypicalPosts.commandStartPost.send(chat);
                    break;
                case "/help":
                    TypicalPosts.commandHelpPost.send(chat);
                    break;
                case "/add":
                    chat.setCondition("/add");
                    TypicalPosts.commandAddPost.send(chat);
                    break;
                case "/pause":
                    chat.setCondition("/pause");
                    TypicalPosts.commandPausePost.send(chat);
                case "/continue":
                    chat.setCondition("/continue");
                    TypicalPosts.commandContinuePost.send(chat);
                    break;
                case  "/moo":
                    TypicalPosts.commandMooPost.send(chat);
                    break;
                case "/delete":
                    chat.setCondition("/delete");
                    TypicalPosts.commandDeletePost.send(chat);
                    break;
                case "/killme":
                    chat.deleteFromDB();
            }
        }
    }
}
