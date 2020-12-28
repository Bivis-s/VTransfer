package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class PhotoAlbum extends Photo implements Printable {
    private final String title;
    private final int size;

    public PhotoAlbum(InputMediaPhoto photo, String title, int size) {
        super(photo);
        this.title = title;
        this.size = size;
    }

    public PhotoAlbum(String photoPath, String title, int size) {
        super(photoPath);
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String getText() {
        return String.format("\uD83D\uDCF7 [Фото альбом] %s Фотографий: %s", title, size);
    }
}
