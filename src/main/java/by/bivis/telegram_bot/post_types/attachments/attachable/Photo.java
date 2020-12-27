package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class Photo extends Attachment implements Attachable {

    private final InputMediaPhoto photo;

    public Photo(InputMediaPhoto photo) {
        this.photo = photo;
    }

    public Photo(String photoPath) {
        this.photo = new InputMediaPhoto(photoPath);
    }

    @Override
    public InputMedia getMedia() {
        return photo;
    }

    @Override
    public boolean hasText() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }
}
