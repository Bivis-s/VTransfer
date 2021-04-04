package test_data.post_types;

import by.bivis.telegram_bot.KeyboardGenerator;
import by.bivis.telegram_bot.post_types.*;
import by.bivis.telegram_bot.post_types.attachments.AttachableContainer;
import by.bivis.telegram_bot.post_types.attachments.attachable.Attachable;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static test_data.post_types.ExamplesValues.*;

public class PostExamples {
    private static InlineKeyboardMarkup getTypicalSourceButton() {
        return KeyboardGenerator.createInlineSourceLink(TYPICAL_SOURCE_NAME, TYPICAL_SOURCE_URL);
    }

    public static Sendable getSimplePost() {
        return Post.builder()
                .text("Post\n" + TYPICAL_POST_TEXT)
                .build();
    }

    public static Sendable getSimpleReplyKeyboardPost() {
        return Post.builder()
                .text("Post с клавиатурой и полем ввода\n" + TYPICAL_POST_TEXT)
                .replyKeyboard(KeyboardGenerator.createReplyKeyboardMarkup(TYPICAL_REPLY_KEYBOARD_COLUMNS,
                        TYPICAL_REPLY_KEYBOARD_STRINGS))
                .build();
    }

    public static Sendable getTextPostWithTextOnly() {
        return TextPost.builder()
                .sourceName(TYPICAL_SOURCE_NAME)
                .text("TextPost\n" + TYPICAL_POST_TEXT)
                .inlineKeyboardMarkup(getTypicalSourceButton())
                .build();
    }

    public static Sendable getTextPostWithTextAndKeyboard() {
        return TextPost.builder()
                .sourceName(TYPICAL_SOURCE_NAME)
                .text("TextPost с кнопкой под сообщением\n" + TYPICAL_POST_TEXT)
                .inlineKeyboardMarkup(getTypicalSourceButton())
                .build();
    }

    //TODO Reply-TextPost should be here

    public static Sendable getTextPostWithTextAndPrintableAttachmentList(List<Printable> attachmentList) {
        return TextPost.builder()
                .sourceName(TYPICAL_SOURCE_NAME)
                .text("TextPost\n" + TYPICAL_POST_TEXT)
                .printableAttachmentList(attachmentList)
                .inlineKeyboardMarkup(getTypicalSourceButton())
                .build();
    }

    public static Sendable getMediaPostWithAttachableAttachment(Attachable attachment) {
        return MediaPost.builder()
                .sourceName(TYPICAL_SOURCE_NAME)
                .attachableAttachment(attachment)
                .text("MediaPost\n" + TYPICAL_POST_TEXT)
                .inlineKeyboardMarkup(getTypicalSourceButton())
                .build();
    }

    public static Sendable getMediaPostWithAttachableAndPrintableAttachment(Attachable attachableList,
                                                                            List<Printable> printableList) {
        return MediaPost.builder()
                .sourceName(TYPICAL_SOURCE_NAME)
                .attachableAttachment(attachableList)
                .text("MediaPost\n" + TYPICAL_POST_TEXT)
                .printableAttachmentList(printableList)
                .inlineKeyboardMarkup(getTypicalSourceButton())
                .build();
    }

//    public static Sendable getMediaGroupPostWithPrintableAttachment(List<Attachable> attachableList,
//                                                                    List<Printable> printableList) {
//        MediaGroupPost.builder()
//                .sourceName(TYPICAL_SOURCE_NAME)
//                .attachableAttachments(attachableList)
//                .printableAttachmentList(printableList)
//                .text("MediaGroupPost\n" + TYPICAL_POST_TEXT)
//                .inlineKeyboardMarkup(getTypicalSourceButton())
//                .build();
//    }
}
