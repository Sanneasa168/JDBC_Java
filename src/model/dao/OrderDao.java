package model.dao;

import model.entity.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    List<Order> gueryAllOrders();
    Order  searchByIdOrder(Integer id);
    int updateOrderById(Integer id);
    int deleteOrerById(Integer id);
}
