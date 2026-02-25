package repository.custom.impl;

import model.dto.MedicineDTO;
import model.entity.Medicine;
import repository.custom.MedicineRepository;

import java.sql.SQLException;
import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    @Override
    public boolean create(Medicine medicine) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Medicine medicine) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public Medicine getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        return List.of();
    }
}
