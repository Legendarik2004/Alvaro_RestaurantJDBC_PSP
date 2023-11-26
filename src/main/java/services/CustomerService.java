package services;

import domain.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer get(int id);

    int save(Customer c);

    int update(Customer c);

    int delete(int id);
}
