package test_data.post_types.media_posts;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.testng.annotations.Test;
import test_data.post_types.AttachableAttachmentsExamples;
import test_data.post_types.InitBotBeforeTest;
import test_data.post_types.PostExamples;
import test_data.post_types.PrintableAttachmentsExamples;

public class MediaPostTest extends InitBotBeforeTest {

    @Test
    public void PhotoMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getPhoto()).send(chat);
    }

    @Test
    public void AudioMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getAudio()).send(chat);
    }

    @Test
    public void DocumentMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getDocument()).send(chat);
    }

    @Test
    public void GoodSelectionMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getGoodSelection()).send(chat);
    }

    @Test
    public void PhotoAlbumMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getPhotoAlbum()).send(chat);
    }

    @Test
    public void VideoMediaPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getVideo()).send(chat);
    }

    @Test
    public void VideoWithoutDescriptionPostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAttachment(AttachableAttachmentsExamples.getVideoWithoutDescription())
                .send(chat);
    }

    @Test
    public void PhotoAlbumMediaAndAllPrintablePostTest() throws TelegramApiException {
        PostExamples.getMediaPostWithAttachableAndPrintableAttachment(AttachableAttachmentsExamples
                .getVideoWithoutDescription(), PrintableAttachmentsExamples.getAllPrintable()).send(chat);
    }
}
