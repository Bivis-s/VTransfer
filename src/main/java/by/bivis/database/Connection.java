package by.bivis.database;

import by.bivis.telegram_bot.users.User;
import by.bivis.vkParser.sources.Source;

public class Connection implements SQLAble{
    private final User user;
    private final Source source;

    public Connection(User user, Source source) {
        this.user = user;
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public String toSQLString() {
        return user.getId() + ", " + source.getId();
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "userId=" + user +
                ", sourceId=" + source +
                '}';
    }
}
