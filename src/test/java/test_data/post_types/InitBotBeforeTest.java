package test_data.post_types;

import by.bivis.telegram_bot.Run;
import by.bivis.telegram_bot.TelegramChat;
import org.testng.annotations.BeforeMethod;

import static test_data.post_types.ExamplesValues.TYPICAL_CHAT_ID;

public class InitBotBeforeTest {
    protected TelegramChat chat;

    @BeforeMethod
    public void initBot() throws Exception {
        Run.main();
        chat = new TelegramChat(TYPICAL_CHAT_ID, Run.telegramBot);
    }
}
