package services.impl;

import dao.OrderDAO;
import jakarta.inject.Inject;
import domain.model.Order;
import services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    @Inject
    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> getAll() {
        return dao.getAll();
    }

    @Override
    public Order get(int id) {
        return dao.get(id);
    }

    @Override
    public int save(Order order) {
        return dao.save(order);
    }

    @Override
    public int update(Order order) {
        return dao.update(order);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }
}
