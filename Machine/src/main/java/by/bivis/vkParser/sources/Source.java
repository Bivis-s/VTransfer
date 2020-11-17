package by.bivis.vkParser.sources;

import by.bivis.database.SQLAble;

public class Source implements SQLAble {
    private final int id; //126771813 (don't forget "-"126771813 for groups)
    private final String screen_name; // name in url
    private final String name; // just name

    public Source(int id, String screen_name, String name) {
        this.id = id;
        this.screen_name = screen_name;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", screen_name='" + screen_name + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public String toSQLString() {
        return id + ", " + "'" + screen_name + "', " + "'" + name + "'";
    }
}
