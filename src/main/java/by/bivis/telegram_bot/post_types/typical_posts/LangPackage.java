package by.bivis.telegram_bot.post_types.typical_posts;

import by.bivis.telegram_bot.KeyboardGenerator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface LangPackage {
    String getStartMessage();
    default ReplyKeyboardMarkup getStartMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(2, "/add", "/help");
    }

    String getHelpMessage();
    default ReplyKeyboardMarkup getHelpMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(3,
                "/help", "/continue", "/pause", "/delete", "/killme", "/moo", "/add");
    }

    String getAddMessage();
    default ReplyKeyboardMarkup getAddMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(1, "/continue");
    }

    String getContinueMessage();

    default String getMooMessage() {
        return "\\|/        (__)   moo \n" +
                "   `\\-----(oo)\n" +
                "      ||   (__)\n" +
                "      ||w---||     \\|/\n" +
                "  \\|/";
    }
    default ReplyKeyboardMarkup getMooMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(2, "/continue", "/moo", "/add");
    }

    String getPauseMessage();
    default ReplyKeyboardMarkup getPauseMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(1, "/continue");
    }

    //TODO заменить строки в клавиатуре на названия групп
    String getDeleteMessage();
    default ReplyKeyboardMarkup getDeleteMessageReplyKeyboard() {
        return KeyboardGenerator.createReplyKeyboardMarkup(3, "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue", "/continue");
    }
}
