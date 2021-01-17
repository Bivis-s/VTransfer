package by.bivis.telegram_bot.post_types.attachments.attachable;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.io.File;

public class Photo implements Attachable {
    private File photo;

    public Photo() {
    }

    public Photo setPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public Photo setPhoto(String photoPath) {
        this.photo = new File(photoPath);
        return this;
    }

    @Override
    public InputFile getInputFile() {
        return new InputFile(photo);
    }

    @Override
    public InputMedia getInputMedia() {
        return new InputMediaPhoto(photo.getPath());
    }
}
