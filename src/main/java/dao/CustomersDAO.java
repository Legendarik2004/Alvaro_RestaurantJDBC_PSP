package dao;

import domain.model.Customer;

import java.util.List;

public interface CustomersDAO {

    List<Customer> getAll();

    Customer get(int id);

    int save(Customer c);

    int update(Customer c);

    int delete(int id);
}
