package by.bivis.telegram_bot.post_types.typical_posts;

import by.bivis.telegram_bot.post_types.Post;
import by.bivis.telegram_bot.post_types.Sendable;

public class TypicalPosts {
    private static final LangPackage langPackage = new LangPackageRu();

    /**
     * Message sends after receiving /start command
     */
    public static Sendable commandStartPost = Post.builder()
            .text(langPackage.getStartMessage())
            .replyKeyboard(langPackage.getStartMessageReplyKeyboard())
            .build();
    public static Sendable commandHelpPost = Post.builder()
            .text(langPackage.getHelpMessage())
            .replyKeyboard(langPackage.getHelpMessageReplyKeyboard())
            .build();
    public static Sendable commandAddPost = Post.builder()
            .text(langPackage.getAddMessage())
            .replyKeyboard(langPackage.getAddMessageReplyKeyboard())
            .build();
    public static Sendable commandContinuePost = Post.builder()
            .text(langPackage.getContinueMessage())
            .build();
    public static Sendable commandMooPost = Post.builder()
            .text(langPackage.getMooMessage())
            .replyKeyboard(langPackage.getMooMessageReplyKeyboard())
            .build();
    public static Sendable commandPausePost = Post.builder()
            .text(langPackage.getPauseMessage())
            .build();
    public static Sendable commandDeletePost = Post.builder()
            .text(langPackage.getDeleteMessage())
            .replyKeyboard(langPackage.getDeleteMessageReplyKeyboard())
            .build();
}
