package by.bivis.telegram_bot.post_types;

import by.bivis.telegram_bot.TelegramChat;
import by.bivis.telegram_bot.post_types.attachments.attachable.*;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
public class MediaPost extends TextPost implements Sendable {
    private final Attachable attachableAttachment;

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

    //TODO убрать дубликаты кода написав фасад для SendPhoto, SendAudio, SendDocument, SendVideo.
    private void sendPhoto(TelegramChat chat) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(attachableAttachment.getInputFile());
        sendPhoto.setChatId(chat.getChatId());
        if (replyPostId != 0) {
            sendPhoto.setReplyToMessageId(replyPostId);
        }
        sendPhoto.setCaption(getFormattedSourceName() + getFormattedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendPhoto);
    }

    private void sendVideo(TelegramChat chat) throws TelegramApiException {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setVideo(attachableAttachment.getInputFile());
        sendVideo.setChatId(chat.getChatId());
        if (replyPostId != 0) {
            sendVideo.setReplyToMessageId(replyPostId);
        }
        sendVideo.setCaption(getFormattedSourceName() + ((Printable) attachableAttachment).getFormattedText() + "\n" +
                getFormattedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendVideo.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendVideo);
    }

    private void sendDocument(TelegramChat chat) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setDocument(attachableAttachment.getInputFile());
        sendDocument.setChatId(chat.getChatId());
        if (replyPostId != 0) {
            sendDocument.setReplyToMessageId(replyPostId);
        }
        sendDocument.setCaption(getFormattedSourceName() + ((Printable) attachableAttachment).getFormattedText() +
                "\n" + getFormattedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendDocument.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendDocument);
    }

    private void sendAudio(TelegramChat chat) throws TelegramApiException {
        SendAudio sendAudio = new SendAudio();
        sendAudio.setAudio(attachableAttachment.getInputFile());
        sendAudio.setChatId(chat.getChatId());
        if (replyPostId != 0) {
            sendAudio.setReplyToMessageId(replyPostId);
        }
        sendAudio.setCaption(getFormattedSourceName() + ((Printable) attachableAttachment).getFormattedText() + "\n" +
                getFormattedText() + formatPrintableAttachments());
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
        if (replyPostId != 0) {
            sendPhoto.setReplyToMessageId(replyPostId);
        }
        sendPhoto.setCaption(getFormattedSourceName() + ((Printable) attachableAttachment).getFormattedText() + "\n" +
                getFormattedText() + formatPrintableAttachments());
        if (inlineKeyboardMarkup != null) {
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }
        (chat.getBot()).execute(sendPhoto);
    }
}
