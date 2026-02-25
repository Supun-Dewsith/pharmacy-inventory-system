package repository.custom.impl;

import model.entity.RecentActivity;
import repository.custom.RecentActivityRepository;

import java.sql.SQLException;
import java.util.List;

public class RecentActivityRepositoryImpl implements RecentActivityRepository {
    @Override
    public boolean create(RecentActivity recentActivity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RecentActivity recentActivity) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public RecentActivity getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<RecentActivity> getAll() throws SQLException {
        return List.of();
    }
}
