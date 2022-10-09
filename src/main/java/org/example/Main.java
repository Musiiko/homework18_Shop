package org.example;

import org.example.domain.Order;
import org.example.domain.Product;
import org.example.service.SelectService;
import org.example.service.UpdateService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        Product phone = new Product(12.45f, "Phone", "Samsung X5");
        Order order = new Order(LocalDate.now(), "S14");
        UpdateService updateService = new UpdateService();
        updateService.insertProduct(phone);
        updateService.insertOrder(order);
        updateService.addProductToOrder(2, 3, 1);

        SelectService selectService = new SelectService();
        selectService.showOrdersBySumAndCount(85.00f, 3);

        selectService.showOrdersByProductName("Phone");
        selectService.showOrdersWithoutProductAndCurrentDate("Charger");
        updateService.deleteInfoOrder(3, 1);
    }
}