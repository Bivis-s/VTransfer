package by.bivis.telegram_bot.post_types.attachments.attachable;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class Photo implements Attachable {
    private String photoPath;

    public Photo() {
    }

    public Photo setPhoto(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    @Override
    public InputFile getInputFile() {
        return new InputFile(photoPath);
    }

    @Override
    public InputMediaPhoto getInputMedia() {
        return new InputMediaPhoto(photoPath);
    }
}
