package repository.custom.impl;

import model.dto.MedicineDTO;
import model.entity.Medicine;
import repository.custom.MedicineRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    @Override
    public boolean create(Medicine medicine) throws SQLException {
        System.out.println(medicine.toString());
        return false;
    }

    @Override
    public boolean update(Medicine medicine) throws SQLException {
        System.out.println(medicine.toString());
        return false;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        System.out.println(id);
        return false;
    }

    @Override
    public Medicine getById(Long id) throws SQLException {
        System.out.println(id);
        return null;
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        //test data
        medicineList.add(new Medicine((long)1,"MED-101", "Amoxicillin", "Amoxil", "BN-7721", "500mg Capsules", "Antibiotic", 15.50, 10.00, 4, 50, "10x10 Blister", LocalDate.of(2025, 5, 12), "SUP-001"));
        medicineList.add(new Medicine((long)2,"MED-102", "Paracetamol", "Panadol", "L-4409", "500mg Tablets", "Analgesic", 4.25, 2.10, 1200, 100, "1000 Bulk Pack", LocalDate.of(2024, 12, 1), "SUP-002"));
        medicineList.add(new Medicine((long)3,"MED-103", "Cetirizine", "Zyrtec", "BN-9902", "10mg Tablets", "Antihistamine", 12.00, 7.50, 300, 30, "30 Tabs Pack", LocalDate.of(2027, 2, 15), "SUP-001"));
        medicineList.add(new Medicine((long)4,"MED-104", "Metformin", "Glucophage", "TX-1142", "850mg Tablets", "Antidiabetic", 18.75, 11.20, 550, 40, "14x4 Strip", LocalDate.of(2026, 9, 20), "SUP-003"));
        medicineList.add(new Medicine((long)5,"MED-105", "Omeprazole", "Prilosec", "BN-3381", "20mg Capsules", "Antacid", 24.50, 15.00, 180, 25, "28 Caps Bottle", LocalDate.of(2027, 1, 30), "SUP-002"));
        medicineList.add(new Medicine((long)6,"MED-106", "Atorvastatin", "Lipitor", "L-6671", "10mg Tablets", "Statins", 32.00, 20.50, 210, 20, "30 Tabs Pack", LocalDate.of(2027, 8, 10), "SUP-004"));
        medicineList.add(new Medicine((long)7,"MED-107", "Ibuprofen", "Advil", "BN-5540", "400mg Tablets", "NSAID", 8.90, 4.50, 640, 50, "20 Tabs Strip", LocalDate.of(2026, 11, 5), "SUP-001"));
        medicineList.add(new Medicine((long)8,"MED-108", "Salbutamol", "Ventolin", "V-9002", "100mcg Inhaler", "Bronchodilator", 45.00, 28.00, 95, 15, "200 Dose Unit", LocalDate.of(2027, 3, 25), "SUP-005"));
        medicineList.add(new Medicine((long)9,"MED-109", "Losartan", "Cozaar", "BN-2219", "50mg Tablets", "Antihypertensive", 21.30, 13.40, 290, 30, "30 Tabs Pack", LocalDate.of(2026, 7, 14), "SUP-003"));
        medicineList.add(new Medicine((long)10,"MED-110", "Azithromycin", "Zithromax", "BN-8831", "250mg Tablets", "Antibiotic", 38.00, 24.00, 145, 10, "6 Tabs Pack", LocalDate.of(2027, 6, 18), "SUP-004"));
        return medicineList;
    }
}
