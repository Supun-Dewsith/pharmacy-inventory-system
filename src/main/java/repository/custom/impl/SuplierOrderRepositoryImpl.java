package repository.custom.impl;

import model.dto.SaleDTO;
import model.entity.SuplierOrder;
import repository.custom.SuplierOrderRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SuplierOrderRepositoryImpl implements SuplierOrderRepository {

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
