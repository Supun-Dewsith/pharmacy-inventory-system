package repository.custom.impl;

import model.entity.SuplierOrder;
import repository.custom.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public boolean create(SuplierOrder suplierOrder) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SuplierOrder suplierOrder) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public SuplierOrder getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<SuplierOrder> getAll() throws SQLException {
        return List.of();
    }
}
