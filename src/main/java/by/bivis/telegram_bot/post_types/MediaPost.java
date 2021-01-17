package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.attachable.*;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * There must be only 1 Attachable Attachment
 * (Printable Attachment may be many)
 */
public class MediaPost extends TextPost implements Sendable {
    private Attachable attachableAttachment;

    public MediaPost() {
    }

    @Override
    public MediaPost setText(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public MediaPost setReplyPost(Sendable replyPost) {
        super.setReplyPost(replyPost);
        return this;
    }

    @Override
    public MediaPost setInlineKeyboardMarkup(InlineKeyboardMarkup inlineKeyboardMarkup) {
        super.setInlineKeyboardMarkup(inlineKeyboardMarkup);
        return this;
    }

    @Override
    public MediaPost setPrintableAttachmentList(List<Printable> printableAttachmentList) {
        super.setPrintableAttachmentList(printableAttachmentList);
        return this;
    }

    public MediaPost setAttachableAttachment(Attachable attachment) {
        this.attachableAttachment = attachment;
        return this;
    }

    @Override
    public void send(TelegramChat... chats) throws TelegramApiException {
        if (!isReply()) {
            for (TelegramChat chat : chats) {
                if (attachableAttachment.getClass().equals(Photo.class)) {
                    sendPhoto(chat);
                } else if (attachableAttachment.getClass().equals(Video.class)) {
                    sendVideo(chat);
                } else if (attachableAttachment.getClass().equals(Document.class)) {
                    sendDocument(chat);
                } else if (attachableAttachment.getClass().equals(Audio.class)) {
                    sendAudio(chat);
                } else if (attachableAttachment.getClass().equals(PhotoAlbum.class)) {
                    sendPhotoWithFormattedText(chat);
                } else if (attachableAttachment.getClass().equals(GoodSelection.class)) {
                    sendPhotoWithFormattedText(chat);
                }
            }
        } else {
            //TODO Implement sending reply-Posts
        }
    }

    private void sendPhoto(TelegramChat chat) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(attachableAttachment.getInputFile());
        sendPhoto.setChatId(chat.getChatId());
        sendPhoto.setCaption(getSeparatedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendPhoto);
    }

    private void sendVideo(TelegramChat chat) throws TelegramApiException {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setVideo(attachableAttachment.getInputFile());
        sendVideo.setChatId(chat.getChatId());
        sendVideo.setCaption(((Printable) attachableAttachment).getFormattedText() + "\n" +
                getSeparatedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendVideo.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendVideo);
    }

    private void sendDocument(TelegramChat chat) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setDocument(attachableAttachment.getInputFile());
        sendDocument.setChatId(chat.getChatId());
        sendDocument.setCaption(((Printable) attachableAttachment).getFormattedText() + "\n" +
                getSeparatedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendDocument.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendDocument);
    }

    private void sendAudio(TelegramChat chat) throws TelegramApiException {
        SendAudio sendAudio = new SendAudio();
        sendAudio.setAudio(attachableAttachment.getInputFile());
        sendAudio.setChatId(chat.getChatId());
        sendAudio.setCaption(((Printable) attachableAttachment).getFormattedText() + "\n" +
                getSeparatedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendAudio.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendAudio);
    }

    /**
     * Sends messages containing photo and formatted text
     * Using for sending Posts with PhotoAlbum or GoodSelection
     *
     * @param chat TelegramChat
     * @throws TelegramApiException Exception
     */
    private void sendPhotoWithFormattedText(TelegramChat chat) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(attachableAttachment.getInputFile());
        sendPhoto.setChatId(chat.getChatId());
        sendPhoto.setCaption(((Printable) attachableAttachment).getFormattedText() + "\n" +
                getSeparatedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendPhoto);
    }
}
