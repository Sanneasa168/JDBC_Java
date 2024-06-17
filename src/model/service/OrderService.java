package model.service;

import model.dao.OrderDao;
import model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();
    List<OrderDao> addNewOrders(OrderDto createOrderDto);
    List<OrderDto> deleteOrder(Integer orderId);
}
