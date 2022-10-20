package org.example.service;

import org.example.DbConnector;
import org.example.domain.Order;
import org.example.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectService {
    public Order showOrderByNumber(String number) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT * FROM orders o\n" +
                                "WHERE o.order_number = ?;");
        statement.setString(1, number);

        Order order = null;

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String orderNumber = resultSet.getString("order_number");
                Date date = resultSet.getDate("order_date");

                order = new Order(orderId, orderNumber, date.toLocalDate());
            }

            List<Product> products = findProductsByOrderId(order.getId());

            order.setProducts(products);
        }

        return order;

    }

    private List<Product> findProductsByOrderId(int orderId) throws SQLException {
        List<Product> products = new ArrayList<>();

        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT * FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "WHERE po.FK_Order = ?;");
        statement.setInt(1, orderId);

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");

                Product product = new Product(productId, price, name, description);

                products.add(product);
            }
        }
        return products;
    }

    public List<Order> showOrdersBySumAndCount(float sum, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT o.order_number, o.order_id, o.order_date, " +
                                "sum(pr.price) sum_order, count(*) count\n " +
                                "FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "INNER JOIN orders o\n" +
                                "ON o.order_id = po.FK_Order\n" +
                                "GROUP BY(o.order_number)\n" +
                                "HAVING sum_order < ? AND count = ?;");

        statement.setFloat(1, sum);
        statement.setInt(2, count);

        List<Order> orders = new ArrayList<>();

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String orderNumber = resultSet.getString("order_number");
                Date date = resultSet.getDate("order_date");

                List<Product> products = findProductsByOrderId(orderId);

                Order order = new Order(orderId, orderNumber, date.toLocalDate());

                order.setProducts(products);

                orders.add(order);

            }
        }

        return orders;
    }

    public List<Order> showOrdersByProductName(String name) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT o.order_number, o.order_id, o.order_date " +
                                "FROM products pr\n" +
                                "INNER JOIN product_order po\n" +
                                "ON pr.product_id = po.FK_Product\n" +
                                "INNER JOIN orders o\n" +
                                "ON o.order_id = po.FK_Order\n" +
                                "WHERE pr.name = ?;");

        statement.setString(1, name);

        List<Order> orders = new ArrayList<>();

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String orderNumber = resultSet.getString("order_number");
                Date date = resultSet.getDate("order_date");

                List<Product> products = findProductsByOrderId(orderId);

                Order order = new Order(orderId, orderNumber, date.toLocalDate());

                order.setProducts(products);

                orders.add(order);
            }
        }

        return orders;
    }

    public List<Order> showOrdersWithoutProductAndCurrentDate(String productName) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT DISTINCT(o.order_number), o.order_id, o.order_date\n " +
                                "FROM products pr\n " +
                                "INNER JOIN product_order po\n " +
                                "ON pr.product_id = po.FK_Product\n " +
                                "INNER JOIN orders o\n " +
                                "ON o.order_id = po.FK_Order\n " +
                                "WHERE pr.name != ? AND o.order_date = curdate();");

        statement.setString(1, productName);

        List<Order> orders = new ArrayList<>();

        try (ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String orderNumber = resultSet.getString("order_number");
                Date date = resultSet.getDate("order_date");

                List<Product> products = findProductsByOrderId(orderId);

                Order order = new Order(orderId, orderNumber, date.toLocalDate());

                order.setProducts(products);

                orders.add(order);

            }
        }

        return orders;
    }
}
