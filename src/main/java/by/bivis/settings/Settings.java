package by.bivis.settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    public static String infoText = "Приветстую тебя, странник" +
            "\n" +
            "Если ты давно хотел избавить себя от тяготы нахождения в соцсети ВКонтакте, но тебя останавливало наличие там уникального контента, то поздравляю- проблема решена." +
            "\n" +
            "Этот бот позволяет перенести все твои подписки из отвртительного ВКонтакта в прекрасный Телеграм, пользуйся!" +
            "\n" +
            "-----" +
            "\n" +
            "Список команд:" +
            "\n" +
            "/start" +
            "\n" +
            "/info - ты сейчас тут" +
            "\n" +
            "/add - активирует режим добавления групп. После её ввода каждое сообщение будет восприниматься как запрос на подписку, до ввода следующей команды" +
            "\n" +
            "/continue - сброс всех режимов и включение режима парсинга" +
            "\n" +
            "/delete - активирует режим удаления групп" +
            "\n" +
            "/stop - останавливает режим парсинга" +
            "\n" +
            "/killme - удаление тебя из базы данных, полный сброс";

    public static String getSetting(String Property) throws IOException {
        File cfgFile = new File("./src/main/resources/tokens.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(cfgFile));
        return properties.getProperty(Property);
    }
}
