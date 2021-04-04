package test_data.post_types.text_posts;

import by.bivis.telegram_bot.KeyboardGenerator;
import by.bivis.telegram_bot.post_types.Post;
import by.bivis.telegram_bot.post_types.TextPost;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.testng.annotations.Test;
import test_data.post_types.InitBotBeforeTest;
import test_data.post_types.PostExamples;

import static test_data.post_types.ExamplesValues.*;
import static test_data.post_types.ExamplesValues.TYPICAL_REPLY_KEYBOARD_STRINGS;

//TODO ADD ASSERTIONS
public class TextPostAndPostTest extends InitBotBeforeTest {
    @Test
    public void simplePostTest() throws TelegramApiException {
        PostExamples.getSimplePost().send(chat);
    }

    @Test
    public void simpleReplyKeyboardPostTest() throws TelegramApiException {
        PostExamples.getSimpleReplyKeyboardPost().send(chat);
    }

    @Test
    public void textPostWithTextOnlyTest() throws TelegramApiException {
        PostExamples.getTextPostWithTextOnly().send(chat);
    }

    @Test
    public void textPostWithTextAndKeyboardTest() throws TelegramApiException {
        PostExamples.getTextPostWithTextAndKeyboard().send(chat);
    }

    //ПРОКЛЯТО

    @Test // /info
    public void send1SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Привет!\nЭтот бот поможет смотреть расписание твоего любимого колледжа!\nЧтобы начать необходимо ввести одну из комманд ниже:\n" +
                        "/info - информация о боте и его командах (ты тут)\n" +
                        "/subscribe - подписаться на один из источников (твоя группа именно здесь)\n" +
                        "/get - получить расписание одной из своих подписок\n" +
                        "/see - посмотреть другое расписание, не подписываясь на него\n" +
                        "/notify - настроить ежедневную рассылку расписания из твоих подписок\n" +
                        "/notes - открыть заметки\n")
                .build().send(chat);
    }

    @Test // /subscribe 1
    public void send2SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Выбери категорию, на которую хочешь подписаться")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(4,"Преподаватели", "Группы", "Кабинеты", "Предметы"))
                .build().send(chat);
    }

    @Test // /subscribe 2
    public void send3SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Выбери букву группы, на которую хочешь подписаться")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(4,"П", "Т", "Б", "Д", "Э", "К", "Л", "П"))
                .build().send(chat);
    }

    @Test // /subscribe 3
    public void send4SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Выбери букву группы, на которую хочешь подписаться")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(4,"Б-091", "Б-891", "Б-912", "Б-991"))
                .build().send(chat);
    }

    @Test // /subscribe 4
    public void send5SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Группа Б-912 успешно добавлена!")
                .build().send(chat);
    }

    @Test // /get
    public void send6SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Выберите одну из своих подписок, чтобы посмотреть её расписание")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(4,"Т-891", "Т-892", "Т-893"))
                .build().send(chat);
    }

    @Test // /see
    public void send7SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Выбери категорию, чьё расписание хочешь посмотреть")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(4,"Преподаватели", "Группы", "Кабинеты", "Предметы"))
                .build().send(chat);
    }

    @Test // /notes
    public void send8SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Ваши заметки:\n" +
                        "1. Сделать 23 лабу по КПИЯП\n" +
                        "2. Купить хлеб, масло и колбасу\n" +
                        "3. Там, где нас нет — горит невиданный рассвет\n" +
                        "Где нас нет — море и рубиновый закат\n" +
                        "Где нас нет — лес, как малахитовый браслет\n" +
                        "Где нас нет, на Лебединых островах\n" +
                        "Где нас нет, услышь меня и вытащи из омута\n" +
                        "Веди в мой вымышленный город, вымощенный золотом\n" +
                        "Во сне я вижу дали иноземные\n" +
                        "Где милосердие правит, где берега кисельные")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(3,"Добавить", "Редактировать", "Удалить", "Очистить всё", "Отмена"))
                .build().send(chat);
    }

    @Test // /notify
    public void send9SomeZyskaBotExampleSuperUltimateEditionBruhWhyTheNameIsSoLong() throws TelegramApiException {
        Post.builder()
                .text("Ваши рассылки:\n" +
                        "1. Т-893")
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(3,"Установить", "Настроить", "Удалить", "Отмена"))
                .build().send(chat);
    }
}
