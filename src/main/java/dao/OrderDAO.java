package dao;

import domain.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll();

    Order get(int id);

    int save(Order order);

    int update(Order order);

    int delete(int id);
}
