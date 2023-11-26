package services.impl;

import dao.CustomersDAO;
import jakarta.inject.Inject;
import domain.model.Customer;
import services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;

    @Inject
    public CustomerServiceImpl(CustomersDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Customer> getAll() {
        return dao.getAll();
    }

    @Override
    public Customer get(int id) {
        return dao.get(id);
    }

    @Override
    public int save(Customer c) {return dao.save(c);
    }

    @Override
    public int update(Customer c) {
        return dao.update(c);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }
}
