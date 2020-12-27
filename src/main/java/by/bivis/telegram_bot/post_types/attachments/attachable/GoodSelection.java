package by.bivis.telegram_bot.post_types.attachments.attachable;

import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class GoodSelection extends Photo implements Attachable {

    private final String name;
    private final String price;
    private final int count;

    public GoodSelection(InputMediaPhoto photo, String name, String price) {
        super(photo);
        this.name = name;
        this.price = price;
        this.count = 0;
    }

    public GoodSelection(String photoPath, String name, int count) {
        super(photoPath);
        this.name = name;
        this.price = null;
        this.count = count;
    }

    @Override
    public boolean hasText() {
        return true;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        if (price != null) {
            return String.format("Товар: %s\nЦена: %s", name, price);
        } else {
            return String.format("Подборка товаров: %s\nКоличество: %s", name, count);
        }
    }
}
