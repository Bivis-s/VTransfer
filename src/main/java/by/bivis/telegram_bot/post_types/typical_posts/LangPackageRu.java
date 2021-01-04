package by.bivis.telegram_bot.post_types.typical_posts;


public class LangPackageRu implements LangPackage {

    @Override
    public String getStartMessage() {
        return "Я рад, что мы встретились!\n" +
                "Давай подпишем тебя на парочку групп.\n" +
                "Нажми кнопку /add чтобы сделать это\n" +
                "Или нажми кнопку /help чтобы увидеть список команд\n";
    }

    @Override
    public String getHelpMessage() {
        return "Приветстую тебя, странник\n" +
                "Если ты давно хотел избавить себя от тяготы нахождения в соцсети ВКонтакте, " +
                "но тебя останавливало наличие там уникального контента, то поздравляю- проблема решена.\n" +
                "Этот бот позволяет перенести все твои подписки " +
                "из отвртительного ВКонтакта в прекрасный Телеграм, пользуйся!\n" +
                "-----\n\n" +
                "Список команд:\n" +
                "/start - начало всех начал\n" +
                "/help - ты сейчас тут\n" +
                "/continue - включает режим парсинга\n" +
                "/pause - останавливает режим парсинга\n" +
                "/delete - активирует режим удаления групп\n" +
                "/killme - удаляет тебя из базы данных, полный сброс\n" +
                "/moo - получает коровью силу(!)\n" +
                "/add - активирует режим добавления групп. После её ввода каждое сообщение " +
                "будет восприниматься как запрос на подписку, до ввода следующей команды\n";
    }

    @Override
    public String getAddMessage() {
        return "Начните отправлять ссылки или названия групп\n";
    }

    @Override
    public String getContinueMessage() {
        return "Начинаю парсинг, чтобы добавить больше групп отправьте /add\n";
    }

    @Override
    public String getPauseMessage() {
        return "Парсинг остановлен. Для возобновления отправльте /continue\n";
    }

    @Override
    public String getDeleteMessage() {
        return "Выберите группу для удаления:\n";
    }
}
