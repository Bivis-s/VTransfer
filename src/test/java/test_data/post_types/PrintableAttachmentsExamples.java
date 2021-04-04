package test_data.post_types;

import by.bivis.telegram_bot.post_types.attachments.printable.*;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintableAttachmentsExamples {
    public static Printable getLink() {
        return (new Link())
                .setUrl("https://www.dotabuff.com/heroes/winning")
                .setTitle("Dota2 top heroes");
    }

    public static Printable getNote() {
        return (new Note())
                .setTitle("My note")
                .setText("To do some things, bruh\n idk");
    }

    public static Printable getPoll() {
        return (new Poll())
                .setQuestion("Что бы вы выбрали?")
                .setAnswers(Arrays.asList("Параллельную вселенную, где произошел геноцид негроидной раса.",
                        "Параллельную вселенную, где все в мире абсолютно равны."));
    }

    public static Printable getWikiPage() {
        return (new WikiPage())
                .setTitle("WHAT IS IT??? WIKI-PAGE???");
    }

    public static List<Printable> getAllPrintable() {
        return Arrays.asList(getPoll(), getNote(), getWikiPage(), getLink());
    }

    @DataProvider(name = "printable attachments")
    public Object[][] providePrintableAttachmentLists() {
        return new Object[][]{
                {Collections.singletonList(getLink())},
                {Collections.singletonList(getNote())},
                {Collections.singletonList(getPoll())},
                {Collections.singletonList(getWikiPage())},
                {}
        };
    }
}
