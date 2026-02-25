package repository.custom.impl;

import model.entity.Suplier;
import repository.custom.SuplierRepository;

import java.sql.SQLException;
import java.util.List;

public class SuplierRepositoryImpl implements SuplierRepository {
    @Override
    public boolean create(Suplier suplier) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Suplier suplier) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public Suplier getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Suplier> getAll() throws SQLException {
        return List.of();
    }
}
