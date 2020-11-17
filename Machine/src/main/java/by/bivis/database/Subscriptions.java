package by.bivis.database;

public class Subscriptions implements SQLAble{
    private final int userId;
    private final int sourceId;

    public Subscriptions(int userId, int sourceId) {
        this.userId = userId;
        this.sourceId = sourceId;
    }

    public int getUserId() {
        return userId;
    }

    public int getSourceId() {
        return sourceId;
    }

    @Override
    public String toSQLString() {
        return userId + ", " + sourceId;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "userId=" + userId +
                ", sourceId=" + sourceId +
                '}';
    }
}
