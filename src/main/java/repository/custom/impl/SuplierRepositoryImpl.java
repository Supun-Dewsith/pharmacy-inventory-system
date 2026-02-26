package repository.custom.impl;

import model.dto.LotDTO;
import model.dto.SuplierDTO;
import model.entity.Lot;
import model.entity.Suplier;
import model.entity.SuplierOrder;
import repository.custom.SuplierRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Suplier> suppliers = new ArrayList<>();
        //test data
        // 1. Supplier: MediLife Pharma
        Suplier s1 = new Suplier(1L, "MediLife Pharma", "Dr. Amal Perera", "0771234567", 3, "contact@medilife.lk", "Active", new ArrayList<>());
        SuplierOrder o1 = new SuplierOrder(101L, 1L, LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 13), new ArrayList<>());
        o1.getLots().add(new Lot(501L, "L-9920", LocalDate.of(2028, 5, 20), "Passed - Sealed", 150.00));
        s1.getOrders().add(o1);

        // 2. Supplier: Global Health Supplies
        Suplier s2 = new Suplier(2L, "Global Health", "Sarah Jenkins", "0112987654", 7, "sales@globalhealth.com", "Active", new ArrayList<>());
        SuplierOrder o2 = new SuplierOrder(102L, 2L, LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 8), new ArrayList<>());
        o2.getLots().add(new Lot(502L, "B-4412", LocalDate.of(2027, 12, 10), "Passed - Cold Chain OK", 450.00));
        s2.getOrders().add(o2);

        // 3. Supplier: BioTech Labs (Delayed Supplier)
        Suplier s3 = new Suplier(3L, "BioTech Labs", "Mark Fernando", "0714455667", 14, "orders@biotech.lk", "Active", new ArrayList<>());
        SuplierOrder o3 = new SuplierOrder(103L, 3L, LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 19), new ArrayList<>());
        o3.getLots().add(new Lot(503L, "BT-001", LocalDate.of(2026, 6, 15), "Passed", 1200.00));
        s3.getOrders().add(o3);

        // 4. Supplier: QuickMeds Distributing
        Suplier s4 = new Suplier(4L, "QuickMeds", "Nimali Silva", "0332211445", 2, "info@quickmeds.lk", "Active", new ArrayList<>());
        SuplierOrder o4 = new SuplierOrder(104L, 4L, LocalDate.of(2026, 2, 20), LocalDate.of(2026, 2, 22), new ArrayList<>());
        o4.getLots().add(new Lot(504L, "QM-987", LocalDate.of(2029, 1, 01), "Passed - Express Delivery", 85.00));
        s4.getOrders().add(o4);

        // 5. Supplier: Apex Care (Suspended)
        Suplier s5 = new Suplier(5L, "Apex Care", "John Doe", "0759988776", 5, "admin@apexcare.lk", "Suspended", new ArrayList<>());
        SuplierOrder o5 = new SuplierOrder(105L, 5L, LocalDate.of(2025, 12, 15), LocalDate.of(2025, 12, 20), new ArrayList<>());
        o5.getLots().add(new Lot(505L, "AX-220", LocalDate.of(2026, 3, 15), "Failed - Damaged Box", 300.00));
        s5.getOrders().add(o5);
        s5.getOrders().add(o4);
        s5.getOrders().add(o3);
        s5.getOrders().add(o2);
        s5.getOrders().add(o1);

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        return suppliers;
    }
}
