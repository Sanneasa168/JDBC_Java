package model.service;

import mapper.OrdersMapper;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    private  OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<OrderDto> getAllOrders() {
        return orderDao.gueryAllOrders()
                .stream()
                .map(OrdersMapper::formOderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDao> addNewOrders(OrderDto createOrderDto) {
        return  orderDao.addNewOrder(createOrderDto);
    }

    @Override
    public List<OrderDto> deleteOrder(Integer id) {
        return  orderDao.deleteOrerById(id);
    }
}
