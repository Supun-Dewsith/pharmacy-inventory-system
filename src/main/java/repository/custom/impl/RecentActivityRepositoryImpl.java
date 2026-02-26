package repository.custom.impl;

import model.dto.RecentActivityDTO;
import model.entity.RecentActivity;
import repository.custom.RecentActivityRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        List<RecentActivity> activities = new ArrayList<>();

        activities.add(new RecentActivity((long)1,"New Stock Added (Amoxicillin - BN-22901)", LocalDate.of(2026, 2, 21),  LocalTime.of(9, 15)));
        activities.add(new RecentActivity((long)2,"Sale Completed (Inv #8842)", LocalDate.of(2026, 2, 21), LocalTime.of(10, 30)));
        activities.add(new RecentActivity((long)3,"Low Stock Alert (Salbutamol Inhaler)", LocalDate.of(2026, 2, 21), LocalTime.of(11, 12)));
        activities.add(new RecentActivity((long)4,"Supplier Order Placed (SUP-02)", LocalDate.of(2026, 2, 21), LocalTime.of(13, 5)));
        activities.add(new RecentActivity((long)5,"Expiry Warning (Metformin - 30 days left)", LocalDate.of(2026, 2, 21), LocalTime.of(14, 0)));
        activities.add(new RecentActivity((long)6,"Inventory Audit (Section A completed)", LocalDate.of(2026, 2, 21), LocalTime.of(15, 45)));
        activities.add(new RecentActivity((long)7,"Return Processed (Inv #8830)", LocalDate.of(2026, 2, 21), LocalTime.of(16, 20)));
        activities.add(new RecentActivity((long)8,"Price Updated (Omeprazole 20mg)", LocalDate.of(2026, 2, 21), LocalTime.of(17, 5)));
        activities.add(new RecentActivity((long)9,"Sale Completed (Inv #8843)", LocalDate.of(2026, 2, 21), LocalTime.of(18, 10)));
        activities.add(new RecentActivity((long)10,"System Backup (Automatic Cloud Sync)", LocalDate.of(2026, 2, 21), LocalTime.of(21, 0)));
        return activities;
    }
}
