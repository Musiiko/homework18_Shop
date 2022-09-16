package org.example;

import org.example.domain.Order;
import org.example.domain.Product;
import org.example.service.UpdateService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        Product phone = new Product(12.45f, "Phone", "Samsung X5");
        Order order = new Order(LocalDate.now(), "S14");
//        UpdateService.insertProduct(phone);
//        UpdateService.insertOrder(order);
//        UpdateService.addProductToOrder(2, 3, 1);

//        SelectService.showOrderBySumAndCount(85.00f, 3);

//        SelectService.showOrdersByProductName("Phone");
//        SelectService.showOrdersWithoutProductAndCurrentDate("Charger");
//        UpdateService.deleteInfoOrder(3, 1);
    }
}