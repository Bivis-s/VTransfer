package by.bivis.telegram_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class KeyboardGenerator {

    /**
     * Creates a telegram reply keyboard (under text field in chat) with a specified number of columns
     *
     * For example:
     * We have a buttons "home", "info", "help", "start", "close"
     * Columns count = 2
     *
     * This method will create keyboard like this:
     *
     *      "home", "info"
     *      "help", "start"
     *          "close"
     *
     * (Button "close" length will be the same as both "help" and "start")
     *
     * @param columnCount count of keyboard columns
     * @param strings names of keyboard buttons
     * @return ReplyKeyboardMarkup obj which might me setted to telegrambots Message
     */
    public static ReplyKeyboardMarkup createReplyKeyboardMarkup(int columnCount, String... strings) {

        // init Button Queue from string CharSequence
        Queue<KeyboardButton> buttonQueue = new ArrayDeque<>();
        for (String string : strings) {

            buttonQueue.offer(new KeyboardButton(string));
        }

        List<KeyboardRow> rows = new ArrayList<>();

        // allocate all Buttons in Queue to rows
        boolean state = true;
        while (state) {

            KeyboardRow row = new KeyboardRow();

            for (int j = 0; j < columnCount; j++) {
                if (buttonQueue.peek() != null) {
                    row.add(buttonQueue.poll());
                } else {
                    state = false;
                }
            }
            rows.add(row);
        }

        // init ReplyKeyboardMarkup
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);

        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        keyboard.setSelective(true);

        return keyboard;
    }
}
