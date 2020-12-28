package by.bivis.telegram_bot.users;

import by.bivis.database.SQLAble;

public class User implements SQLAble {
    private final int id;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String languageCode;
    private String condition;


    public User(int id, String name, String firstName, String lastName, String languageCode, String condition) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageCode = languageCode;
        this.condition = condition;
    }

    public User(org.telegram.telegrambots.meta.api.objects.User user, String condition) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.languageCode = user.getLanguageCode();
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toSQLString() {
        return id + ", '" + name + "', '" + firstName + "', '" + lastName + "', '" + languageCode + "', '" + condition + "'";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}
