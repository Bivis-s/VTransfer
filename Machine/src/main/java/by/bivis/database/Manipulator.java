package by.bivis.database;

import by.bivis.telegramBot.users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manipulator {
    final static String dbAddress = "database2.sqlite";
    Connection connection;
    Statement statement;

    private void execute(String q) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
        statement = connection.createStatement();
        statement.execute(q);
    }

    private ResultSet executeDQL(String q) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
        statement = connection.createStatement();
        return statement.executeQuery(q);
    }

    public void insert(String table, SQLAble item) throws SQLException {
        execute("INSERT INTO " + table + " VALUES (" + item.toSQLString() + "); ");
    }

    public boolean findPK(String table, int primary_key) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT id FROM " + table + " WHERE id=" + primary_key + "; ");

        while (resultSet.next()) {
            if (primary_key == resultSet.getInt("id")) {
                return true;
            }
        }
        return false;
    }

    public List<Subscriptions> getSubscriptionsList(User user) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT user_id, source_id FROM connection WHERE user_id=" + user.getId() + "; ");

        List<Subscriptions> subscriptionsList = new ArrayList<>();
        while (resultSet.next()) {
            subscriptionsList.add(new Subscriptions(resultSet.getInt("user_id"), resultSet.getInt("source_id")));
        }
        return subscriptionsList;
    }

    public void removeSubscription(Subscriptions subscription) throws SQLException {
        execute("DELETE FROM connection WHERE user_id=" + subscription.getUserId() + " AND source_id=" + subscription.getSourceId() + "; ");
    }

    public void removeAllSubscriptions(int userId) throws SQLException {
        execute("DELETE FROM connection WHERE user_id=" + userId + "; ");
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

    public String getSourceName(Subscriptions subscriptions) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT name FROM sources WHERE id=" + subscriptions.getSourceId() + "; ");
        resultSet.next();
        return resultSet.getString("name");
    }

    public boolean thereIsUser(User user) throws SQLException {
        return findPK("users", user.getId());
    }

    public String getCondition(User user) throws SQLException {
        ResultSet resultSet = executeDQL("SELECT condition FROM users WHERE id=" + user.getId() + "; ");
        resultSet.next();
        return resultSet.getString("condition");
    }
}
