package by.bivis.telegram_bot.post_types.typical_posts;

import by.bivis.telegram_bot.post_types.Post;
import by.bivis.telegram_bot.post_types.Sendable;

public class TypicalPosts {
    private static final LangPackage langPackage = new LangPackageRu();

    public static Sendable commandStartPost = new Post(langPackage.getStartMessage(),
            langPackage.getStartMessageReplyKeyboard());
    public static Sendable commandHelpPost = new Post(langPackage.getHelpMessage(),
            langPackage.getHelpMessageReplyKeyboard());
    public static Sendable commandAddPost = new Post(langPackage.getAddMessage(),
            langPackage.getAddMessageReplyKeyboard());
    public static Sendable commandContinuePost = new Post(langPackage.getContinueMessage());
    public static Sendable commandMooPost = new Post(langPackage.getMooMessage(),
            langPackage.getMooMessageReplyKeyboard());
    public static Sendable commandPausePost = new Post(langPackage.getPauseMessage());
    public static Sendable commandDeletePost = new Post(langPackage.getDeleteMessage(),
            langPackage.getDeleteMessageReplyKeyboard());
}
