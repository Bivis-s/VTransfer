package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.io.File;

public class PhotoAlbum extends Photo implements Printable {
    private String title;
    private int size;

    public PhotoAlbum() {
    }

    @Override
    public PhotoAlbum setPhoto(File photo) {
        super.setPhoto(photo);
        return this;
    }

    @Override
    public PhotoAlbum setPhoto(String photoPath) {
        super.setPhoto(photoPath);
        return this;
    }

    public PhotoAlbum setTitle(String title) {
        this.title = title;
        return this;
    }

    public PhotoAlbum setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDCF7 [Фото альбом] %s Фотографий: %s", title, size);
    }
}
