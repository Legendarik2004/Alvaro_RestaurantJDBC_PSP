package services;

import domain.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order get(int id);

    int save(Order o);

    int update(Order o);

    int delete(int id);
}
