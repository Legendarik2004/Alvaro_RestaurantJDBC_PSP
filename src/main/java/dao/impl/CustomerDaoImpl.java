package dao.impl;

import common.Constantes;
import common.SQLQueries;
import dao.CustomersDAO;
import dao.DBConnectionPool;
import domain.exceptions.BaseDatosCaidaException;
import domain.exceptions.NotFoundException;
import domain.model.Customer;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class CustomerDaoImpl implements CustomersDAO {
    private final DBConnectionPool db;

    @Inject
    public CustomerDaoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Customer> getAll() {
        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.GETALL_CUSTOMERS)) {

            ResultSet rs = preparedStatement.executeQuery();

            List<Customer> customers = readRS(rs);
            if (!customers.isEmpty()) {
                return customers;
            } else {
                throw new NotFoundException(Constantes.NO_CUSTOMERS_FOUND);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }


    @Override
    public Customer get(int id){

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.GET_CUSTOMERS_BY_ID)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            Customer customer = readRS(rs).stream().findFirst().orElse(null);
            if (customer != null) {
                return customer;
            } else {
                throw new NotFoundException(Constantes.NO_CUSTOMERS_FOUND);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    private List<Customer> readRS(ResultSet rs) {
        try {
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer resultCustomer = new Customer(
                        rs.getInt(Constantes.ID),
                        rs.getString(Constantes.FIRST_NAME),
                        rs.getString(Constantes.LAST_NAME),
                        rs.getString(Constantes.EMAIL),
                        rs.getString(Constantes.PHONE),
                        rs.getDate(Constantes.DATE_OF_BIRTH).toLocalDate()
                );
                customers.add(resultCustomer);
            }
            return customers;
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int save(Customer customer) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.ADD_CUSTOMER)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setDate(5, Date.valueOf(customer.getDob()));

            int rowsAdded = preparedStatement.executeUpdate();

            if (rowsAdded > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.CUSTOMER_NOT_ADDED);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int update(Customer customer) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.UPDATE_CUSTOMER)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setDate(5, Date.valueOf(customer.getDob()));
            preparedStatement.setInt(6, customer.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.CUSTOMER_NOT_UPDATED);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.DELETE_CUSTOMER)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.CUSTOMER_NOT_DELETED);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }
}
