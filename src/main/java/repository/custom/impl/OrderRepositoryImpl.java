package repository.custom.impl;

import model.entity.Order;
import repository.custom.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public boolean create(Order order) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public Order getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return List.of();
    }
}
