package by.bivis.database;

import by.bivis.telegramBot.users.User;
import by.bivis.vkParser.sources.Source;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manipulator {
    final static String dbAddress = "database4.sqlite";
    java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
    Statement statement;

    public Manipulator() throws SQLException {
    }

    private void execute(String q) throws SQLException {
//        connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
        statement = connection.createStatement();
        statement.execute(q);
    }

    private ResultSet executeDQL(String q) throws SQLException {
//        connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
        statement = connection.createStatement();
        return statement.executeQuery(q);
    }

    public void insert(String table, SQLAble item) throws SQLException {
        execute("INSERT INTO " + table + " VALUES (" + item.toSQLString() + "); ");
    }

    public boolean thereIsPK(String table, int primary_key) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT id FROM " + table + " WHERE id=" + primary_key + "; ");

        while (resultSet.next()) {
            if (primary_key == resultSet.getInt("id")) {
                return true;
            }
        }
        return false;
    }

    public void removeByPK(String table, int id) throws SQLException {
        execute("DELETE FROM " + table + " WHERE id=" + id + "; ");
    }

    public void resetUser(User user) throws SQLException {
        removeByPK("users", user.getId());
        insert("users", user);
    }

    public void deleteUser(User user) throws SQLException {
        removeByPK("users", user.getId());
    }

    public void updateCondition(User user) throws SQLException {
        execute("UPDATE users SET condition='" + user.getCondition() + "' WHERE id=" + user.getId() + "; ");
    }

    public String getSourceName(Connection connection) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT name FROM sources WHERE id=" + connection.getSource().getName() + "; ");
        resultSet.next();
        return resultSet.getString("name");
    }

    public boolean thereIsSource(Source source) throws SQLException {
        return thereIsPK("sources", source.getId());
    }

    public boolean thereIsUser(User user) throws SQLException {
        return thereIsPK("users", user.getId());
    }

    public boolean thereIsConnection(User user, Source source) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT user_id FROM connection WHERE user_id=" + user.getId() + " AND source_id= " + source.getId() + "; ");

        while (resultSet.next()) {
            if (user.getId() == resultSet.getInt("user_id")) {
                return true;
            }
        }
        return false;
    }

    public List<Source> getSubscriptionsList(User user) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT sources.* FROM sources, connection WHERE connection.source_id=sources.id AND connection.user_id=" + user.getId() + "; ");

        List<Source> sources = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String screenName = resultSet.getString("screen_name");
            String name = resultSet.getString("name");
            String photoURL = resultSet.getString("photo_url");
            sources.add(new Source(id, screenName, name, photoURL));
        }
        return sources;
    }

    public void removeConnection(User user, Source source) throws SQLException {
        execute("DELETE FROM connection WHERE user_id=" + user.getId() + " AND source_id=" + source.getId() + "; ");
    }

    public void removeAllConnections(User user) throws SQLException {
        execute("DELETE FROM connection WHERE user_id=" + user.getId() + "; ");
    }

    public void addConnection(User user, Source source) throws SQLException {
        if (!thereIsConnection(user, source)) {
            insert("connection", new Connection(user, source));
        }
    }

    public void addSource (Source source) throws SQLException {
        insert("sources", source);
    }

    public String getCondition(User user) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT condition FROM users WHERE id=" + user.getId() + "; ");
        resultSet.next();
        return resultSet.getString("condition");
    }

    public Source getSourceById (int sourceId) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT * FROM sources WHERE id=" + sourceId + "; ");
        resultSet.next();
        int id = resultSet.getInt("id");
        String screenName = resultSet.getString("screen_name");
        String name = resultSet.getString("name");
        String photoURL = resultSet.getString("photo_url");

        return new Source(id, screenName, name, photoURL);
    }


}
