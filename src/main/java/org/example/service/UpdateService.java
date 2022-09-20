package org.example.service;

import org.example.DbConnector;
import org.example.domain.Order;
import org.example.domain.Product;

import java.sql.*;

public class UpdateService {
    public static void insertProduct(Product product) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Products(price, name, description) VALUES(?,?,?)");
        statement.setFloat(1, product.getPrice());
        statement.setString(2, product.getName());
        statement.setString(3, product.getDescription());

        statement.executeUpdate();
    }

    public static void insertOrder(Order order) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Orders(order_date, order_number) VALUES(?,?)");
        statement.setDate(1, java.sql.Date.valueOf(order.getDate()));
        statement.setString(2, order.getNumber());

        statement.executeUpdate();
    }

    public static void addProductToOrder(int orderId, int productId, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Product_Order(FK_Order, FK_Product, count_products) VALUES(?,?,?)");
        statement.setInt(1, orderId);
        statement.setInt(2, productId);
        statement.setInt(3, count);

        statement.executeUpdate();
    }

    public static void deleteInfoOrder(int productId, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("DELETE FROM product_order po WHERE po.FK_Product = ? AND count_products = ?;");
        statement.setInt(1, productId);
        statement.setInt(2, count);

        statement.executeUpdate();
    }

}
