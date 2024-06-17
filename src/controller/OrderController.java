package controller;

import model.dao.OrderDao;
import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    final OrderService orderService = new OrderServiceImpl();
    public List<OrderDto> getAllOrders() {
        return  orderService.getAllOrders();
    }
   public List<OrderDao> addNewOrder(OrderDto createOrderDto) {
        return  orderService.addNewOrders(createOrderDto);

   }
   public List<OrderDto>deleteOrder(Integer id) {
        return  orderService.deleteOrder(id);
   }



}
