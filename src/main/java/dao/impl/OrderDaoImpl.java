package dao.impl;

import common.Constantes;
import common.SQLQueries;
import dao.DBConnectionPool;
import dao.OrderDAO;
import domain.exceptions.BaseDatosCaidaException;
import domain.exceptions.NotFoundException;
import domain.model.Order;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Log4j2
public class OrderDaoImpl implements OrderDAO {
    private final DBConnectionPool db;
    private int addedOrderId;

    @Inject
    public OrderDaoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Order> getAll() {

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.GETALL_ORDERS)) {

            ResultSet rs = preparedStatement.executeQuery();
            List<Order> orders = readRS(rs);
            if (!orders.isEmpty()) {
                return orders;
            } else {
                throw new NotFoundException(Constantes.NO_ORDERS_FOUND);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public Order get(int id) {

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.GET_ORDERS_BY_ID)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            Order order = readRS(rs).stream().findFirst().orElse(null);
            if (order != null) {
                return order;
            } else {
                throw new NotFoundException(Constantes.NO_ORDERS_FOUND);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    private List<Order> readRS(ResultSet rs) {

        try {
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order resultOrder = new Order(
                        rs.getInt(Constantes.ORDER_ID),
                        rs.getTimestamp(Constantes.ORDER_DATE),
                        rs.getInt(Constantes.CUSTOMER_ID),
                        rs.getInt(Constantes.TABLE_ID));
                orders.add(resultOrder);
            }
            return orders;
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int save(Order order) {

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.ADD_ORDER)) {
            preparedStatement.setTimestamp(1, order.getDate());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getTableId());

            int rowsAdded = preparedStatement.executeUpdate();

            if (rowsAdded > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.ORDER_NOT_ADDED);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int update(Order order) {

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.UPDATE_ORDER)) {

            preparedStatement.setInt(1, order.getTableId());
            preparedStatement.setInt(2, order.getOrderId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.ORDER_NOT_UPDATED);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }

    @Override
    public int delete(int id) {

        try (Connection myConnection = db.getConnection();
             PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.DELETE_ORDER)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                return 0;
            } else {
                throw new NotFoundException(Constantes.ORDER_NOT_DELETED);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BaseDatosCaidaException(Constantes.DB_ERROR);
        }
    }
}
