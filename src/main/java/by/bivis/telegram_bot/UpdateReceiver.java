package by.bivis.telegram_bot;

import by.bivis.telegram_bot.behavioral_template.Menu;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UpdateReceiver {

    public static void handleUpdate(Update update, TelegramChat chat) {
        System.out.println("###" + update.getCallbackQuery());
        Menu menu = new Menu(chat, update);
        try {
            menu.start();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
