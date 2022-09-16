package org.example.service;

import org.example.DbConnector;

import java.sql.*;

public class SelectService {
    public static void showOrdersByNumber(String number) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT pr.price, " +
                                "pr.name, " +
                                "pr.description, " +
                                "o.order_number, " +
                                "o.order_date " +
                        "FROM products pr\n" +
                        "INNER JOIN product_order po\n" +
                        "ON pr.product_id = po.FK_Product\n" +
                        "INNER JOIN orders o\n" +
                        "ON o.order_id = po.FK_Order\n" +
                        "WHERE o.order_number = ?;");
        statement.setString(1, number);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            float price = resultSet.getFloat("price");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String orderNumber = resultSet.getString("order_number");
            Date date = resultSet.getDate("order_date");

            System.out.println(orderNumber + "\t" + date + "\t" + name + "\t" + description + "\t" + price);
        }
    }

    public static void showOrdersBySumAndCount(float sum, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT o.order_number, sum(pr.price) sum_order, count(*) count\n" +
                                "FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "INNER JOIN orders o\n" +
                                "ON o.order_id = po.FK_Order\n" +
                                "GROUP BY(o.order_number)\n" +
                                "HAVING sum_order < ? AND count = ?;");

        statement.setFloat(1, sum);
        statement.setInt(2, count);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String orderNumber = resultSet.getString("order_number");
            System.out.println(orderNumber);
        }
    }

    public static void showOrdersByProductName(String name) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT o.order_number FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "INNER JOIN orders o\n" +
                                "ON o.order_id = po.FK_Order\n" +
                                "WHERE pr.name = ?;");

        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String orderNumber = resultSet.getString("order_number");
            System.out.println(orderNumber);
        }
    }

    public static void showOrdersWithoutProductAndCurrentDate(String name) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT DISTINCT(o.order_number)\n" +
                                "FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "INNER JOIN orders o\n" +
                                "ON o.order_id = po.FK_Order\n" +
                                "WHERE pr.name != ? AND o.order_date = curdate();");

        statement.setString(1, name);

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String orderNumber = resultSet.getString("order_number");
                System.out.println(orderNumber);
            }
        }
    }
}
