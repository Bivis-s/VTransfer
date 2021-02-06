package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.post_types.attachments.printable.Printable;

public class GoodSelection extends Photo implements Printable {
    private String name;
    private String price;
    private int count;

    public GoodSelection() {
    }

    @Override
    public GoodSelection setPhoto(String photoPath) {
        super.setPhoto(photoPath);
        return this;
    }

    public GoodSelection setName(String name) {
        this.name = name;
        return this;
    }

    public GoodSelection setPrice(String price) {
        this.price = price;
        return this;
    }

    public GoodSelection setCount(int count) {
        this.count = count;
        return this;
    }

    //TODO ADD JAVADOC
    @Override
    public String getFormattedText() {
        if (price != null) {
            return String.format("\uD83D\uDECD Товар: %s\nЦена: %s", name, price);
        } else {
            return String.format("\uD83D\uDECD Подборка товаров: %s\nКоличество: %s", name, count);
        }
    }
}
