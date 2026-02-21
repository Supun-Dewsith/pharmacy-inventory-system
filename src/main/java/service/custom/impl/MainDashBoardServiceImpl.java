package service.custom.impl;

import model.MedicineDTO;
import model.RecentActivityDTO;
import service.custom.MainDashBoardService;
import service.custom.MedicineManagementService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainDashBoardServiceImpl implements MainDashBoardService {
    @Override
    public List<MedicineDTO> getAll() throws SQLException {
        List<MedicineDTO> inventory = new ArrayList<>();

        inventory.add(new MedicineDTO("MED-001", "BN-22901", "Amoxicillin 500mg", "Antibiotic", 12.50, 8.00, 500, 50, "10x10 Blister", LocalDate.of(2027, 5, 15), "SUP-01", 100));
        inventory.add(new MedicineDTO("MED-002", "BN-44021", "Paracetamol 500mg", "Analgesic", 5.00, 2.50, 1200, 100, "1000 Bulk", LocalDate.of(2026, 12, 1), "SUP-02", 200));
        inventory.add(new MedicineDTO("MED-003", "BN-88109", "Cetirizine 10mg", "Antihistamine", 8.75, 4.20, 300, 30, "30 Tabs Pack", LocalDate.of(2027, 2, 20), "SUP-01", 60));
        inventory.add(new MedicineDTO("MED-004", "BN-00922", "Metformin 850mg", "Antidiabetic", 15.20, 9.50, 450, 40, "14x4 Strip", LocalDate.of(2026, 9, 10), "SUP-03", 80));
        inventory.add(new MedicineDTO("MED-005", "BN-11553", "Omeprazole 20mg", "Antacid", 22.00, 14.00, 150, 20, "28 Caps Bottle", LocalDate.of(2027, 1, 30), "SUP-02", 40));
        inventory.add(new MedicineDTO("MED-006", "BN-77210", "Atorvastatin 10mg", "Statins", 18.40, 11.00, 280, 25, "30 Tabs Pack", LocalDate.of(2027, 8, 12), "SUP-04", 50));
        inventory.add(new MedicineDTO("MED-007", "BN-33499", "Ibuprofen 400mg", "NSAID", 6.25, 3.10, 600, 50, "20 Tabs Strip", LocalDate.of(2026, 11, 5), "SUP-01", 100));
        inventory.add(new MedicineDTO("MED-008", "BN-66124", "Salbutamol Inhaler", "Bronchodilator", 35.00, 22.00, 85, 10, "200 Dose Unit", LocalDate.of(2027, 3, 25), "SUP-05", 20));
        inventory.add(new MedicineDTO("MED-009", "BN-55088", "Losartan 50mg", "Antihypertensive", 14.90, 8.50, 210, 30, "30 Tabs Pack", LocalDate.of(2026, 7, 18), "SUP-03", 45));
        inventory.add(new MedicineDTO("MED-010", "BN-22331", "Azithromycin 250mg", "Antibiotic", 28.50, 19.00, 120, 15, "6 Tabs Pack", LocalDate.of(2027, 6, 10), "SUP-04", 30));
        return inventory;
    }

    @Override
    public List<RecentActivityDTO> getAllActivity() throws SQLException {
        List<RecentActivityDTO> activities = new ArrayList<>();

        activities.add(new RecentActivityDTO("New Stock Added (Amoxicillin - BN-22901)", LocalDate.of(2026, 2, 21), LocalTime.of(9, 15)));
        activities.add(new RecentActivityDTO("Sale Completed (Inv #8842)", LocalDate.of(2026, 2, 21), LocalTime.of(10, 30)));
        activities.add(new RecentActivityDTO("Low Stock Alert (Salbutamol Inhaler)", LocalDate.of(2026, 2, 21), LocalTime.of(11, 12)));
        activities.add(new RecentActivityDTO("Supplier Order Placed (SUP-02)", LocalDate.of(2026, 2, 21), LocalTime.of(13, 5)));
        activities.add(new RecentActivityDTO("Expiry Warning (Metformin - 30 days left)", LocalDate.of(2026, 2, 21), LocalTime.of(14, 0)));
        activities.add(new RecentActivityDTO("Inventory Audit (Section A completed)", LocalDate.of(2026, 2, 21), LocalTime.of(15, 45)));
        activities.add(new RecentActivityDTO("Return Processed (Inv #8830)", LocalDate.of(2026, 2, 21), LocalTime.of(16, 20)));
        activities.add(new RecentActivityDTO("Price Updated (Omeprazole 20mg)", LocalDate.of(2026, 2, 21), LocalTime.of(17, 5)));
        activities.add(new RecentActivityDTO("Sale Completed (Inv #8843)", LocalDate.of(2026, 2, 21), LocalTime.of(18, 10)));
        activities.add(new RecentActivityDTO("System Backup (Automatic Cloud Sync)", LocalDate.of(2026, 2, 21), LocalTime.of(21, 0)));
        return activities;
    }
}
