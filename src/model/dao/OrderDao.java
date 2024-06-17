package model.dao;

import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;

public interface OrderDao {
    List<OrderDao> addNewOrder(OrderDto order);
    List<Order> gueryAllOrders();
    Order  searchByIdOrder(Integer id);
    int updateOrderById(Integer id);
    List<OrderDto> deleteOrerById(Integer id);
}
