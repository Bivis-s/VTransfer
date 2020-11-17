package by.bivis.telegramBot.users;

import by.bivis.database.SQLAble;

public class User implements SQLAble {
    private final int id;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String languageCode;


    public User(int id, String name, String firstName, String lastName, String languageCode) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageCode = languageCode;
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


    @Override
    public String toSQLString() {
        return id + ", '" + name + "', '" + firstName + "', '" + lastName + "', '" + languageCode + "'";
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
