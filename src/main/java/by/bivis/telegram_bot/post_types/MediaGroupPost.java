//package by.bivis.telegram_bot.post_types;
//
//import by.bivis.telegram_bot.TelegramChat;
//import by.bivis.telegram_bot.post_types.attachments.AttachableContainer;
//import by.bivis.telegram_bot.post_types.attachments.attachable.*;
//import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
//import lombok.experimental.SuperBuilder;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.List;
//
///**
// * There may be many Attachable Attachment
// * (Printable Attachment also may be many)
// */
//@SuperBuilder
//public class MediaGroupPost extends TextPost implements Sendable {
//    private AttachableContainer attachableAttachments;
//
//    public MediaGroupPost setAttachableAttachments(AttachableContainer attachableAttachments) {
//        this.attachableAttachments = attachableAttachments;
//        return this;
//    }
//
//    public MediaGroupPost setAttachableAttachments(List<Attachable> attachableAttachments) {
//        this.attachableAttachments = new AttachableContainer(attachableAttachments);
//        return this;
//    }
//
//    private String getAttachableFormattedText() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("\n");
//        for (Attachable attachment : attachableAttachments) {
//            if (attachment instanceof Printable) {
//                stringBuilder.append(((Printable) attachment).getFormattedText()).append("\n");
//            }
//        }
//        stringBuilder.append("\n");
//        return stringBuilder.toString();
//    }
//
//    /**
//     * Sends two posts: with text and with media (a media-message is a reply to text-message)
//     * {@code SendMediaGroup.setMedias()} requires only files with url-paths
//     *
//     * @param chats objects of TelegramChat
//     * @throws TelegramApiException Exception
//     */
//    @Override
//    public void send(TelegramChat... chats) throws TelegramApiException {
//        for (TelegramChat chat : chats) {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(chat.getChatId());
//            sendMessage.setParseMode("HTML");
//            sendMessage.setText(getFormattedSourceName() + getFormattedText() + formatPrintableAttachments() +
//                    getAttachableFormattedText());
//            if (inlineKeyboardMarkup != null) {
//                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//            }
//            int mainMessageId = (chat.getBot()).execute(sendMessage).getMessageId();
//            //TODO добавить отправку аудио и документами (отдельными от фото и видео)
//
//            if (attachableAttachments.getClassCount(Photo.class) +
//                    attachableAttachments.getClassCount(Video.class) >= 2) {
//                List<InputMedia> inputMediaList = attachableAttachments.getInputMediasClassList(Photo.class);
//                inputMediaList.addAll(attachableAttachments.getInputMediasClassList(Video.class));
//                sendMediaGroup(inputMediaList, chat, mainMessageId);
//            } else if (attachableAttachments.getClassCount(Photo.class) == 1) {
//                (new MediaPost()).setAttachableAttachment(attachableAttachments
//                        .getAttachableClassList(Photo.class)
//                        .get(0))
//                        .setReplyPostId(mainMessageId)
//                        .send(chat);
//            } else if (attachableAttachments.getClassCount(Video.class) == 1) {
//                (new MediaPost()).setAttachableAttachment(attachableAttachments
//                        .getAttachableClassList(Video.class)
//                        .get(0))
//                        .setReplyPostId(mainMessageId)
//                        .send(chat);
//            }
//            if (attachableAttachments.getClassCount(Audio.class) >= 2) {
//                sendMediaGroup(attachableAttachments.getInputMediasClassList(Audio.class), chat, mainMessageId);
//            } else if (attachableAttachments.getClassCount(Audio.class) == 1) {
//                (new MediaPost()).setAttachableAttachment(attachableAttachments
//                        .getAttachableClassList(Audio.class)
//                        .get(0))
//                        .setReplyPostId(mainMessageId)
//                        .send(chat);
//            }
//            if (attachableAttachments.getClassCount(Document.class) >= 2) {
//                sendMediaGroup(attachableAttachments.getInputMediasClassList(Document.class), chat, mainMessageId);
//            } else if (attachableAttachments.getClassCount(Document.class) == 1) {
//                (new MediaPost()).setAttachableAttachment(attachableAttachments
//                        .getAttachableClassList(Document.class)
//                        .get(0))
//                        .setReplyPostId(mainMessageId)
//                        .send(chat);
//            }
//        }
//    }
//
//    // TODO добавить caption в подпись к первому элементу media group
//    private void sendMediaGroup(List<InputMedia> inputMediaList, TelegramChat chat, int mainMessageId)
//            throws TelegramApiException {
//        SendMediaGroup sendMediaGroup = new SendMediaGroup();
//        sendMediaGroup.setChatId(chat.getChatId());
//        sendMediaGroup.setMedias(inputMediaList); // Only files with url-paths are required
//        sendMediaGroup.setAllowSendingWithoutReply(true);
//        sendMediaGroup.setDisableNotification(false);
//        sendMediaGroup.setReplyToMessageId(mainMessageId);
//        (chat.getBot()).execute(sendMediaGroup);
//    }
//}
