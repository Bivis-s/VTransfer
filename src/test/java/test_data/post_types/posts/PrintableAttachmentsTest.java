package test_data.post_types.posts;

import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.testng.annotations.Test;
import test_data.post_types.InitBotBeforeTest;
import test_data.post_types.PostExamples;
import test_data.post_types.PrintableAttachmentsExamples;

import java.util.List;

public class PrintableAttachmentsTest extends InitBotBeforeTest {
    @Test(dataProvider = "printable attachments", dataProviderClass = PrintableAttachmentsExamples.class)
    public void textPostWithTextAndPrintableAttachmentListTest(List<Printable> attachmentList)
            throws TelegramApiException {
        PostExamples.getTextPostWithTextAndPrintableAttachmentList(attachmentList).send(chat);
    }
}
