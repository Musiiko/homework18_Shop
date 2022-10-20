package org.example;

import org.example.domain.Order;
import org.example.domain.Product;
import org.example.service.SelectService;
import org.example.service.UpdateService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Product phone = new Product(12.45f, "Phone", "Samsung X5");
        Order order = new Order(LocalDate.now(), "S14");
        UpdateService updateService = new UpdateService();
        updateService.insertProduct(phone);
        updateService.insertOrder(order);
        updateService.addProductToOrder(1, 1, 2);

        SelectService selectService = new SelectService();
        List<Order> orders = selectService.showOrdersBySumAndCount(185.00f, 3);

        System.out.println(orders);
        List<Order> orders = selectService.showOrdersByProductName("Phone");

        System.out.println(orders);

        List<Order> orders = selectService.showOrdersWithoutProductAndCurrentDate("TV");

        System.out.println(orders);
        updateService.deleteInfoOrder(3, 1);

        System.out.println(selectService.showOrderByNumber("S10"));
    }
}