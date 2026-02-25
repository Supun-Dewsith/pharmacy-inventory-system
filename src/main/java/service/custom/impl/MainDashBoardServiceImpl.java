package service.custom.impl;

import model.dto.MedicineDTO;
import model.dto.PrescriptionDTO;
import model.dto.RecentActivityDTO;
import model.dto.SaleDTO;
import service.custom.MainDashBoardService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainDashBoardServiceImpl implements MainDashBoardService {
    @Override
    public List<MedicineDTO> getAll() throws SQLException {
        List<MedicineDTO> medicineList = new ArrayList<>();
        //test data
        medicineList.add(new MedicineDTO((long)1,"MED-101", "Amoxicillin", "Amoxil", "BN-7721", "500mg Capsules", "Antibiotic", 15.50, 10.00, 450, 50, "10x10 Blister", LocalDate.of(2027, 5, 12), "SUP-001"));
        medicineList.add(new MedicineDTO((long)2,"MED-102", "Paracetamol", "Panadol", "L-4409", "500mg Tablets", "Analgesic", 4.25, 2.10, 1200, 100, "1000 Bulk Pack", LocalDate.of(2026, 12, 1), "SUP-002"));
        medicineList.add(new MedicineDTO((long)3,"MED-103", "Cetirizine", "Zyrtec", "BN-9902", "10mg Tablets", "Antihistamine", 12.00, 7.50, 300, 30, "30 Tabs Pack", LocalDate.of(2027, 2, 15), "SUP-001"));
        medicineList.add(new MedicineDTO((long)4,"MED-104", "Metformin", "Glucophage", "TX-1142", "850mg Tablets", "Antidiabetic", 18.75, 11.20, 550, 40, "14x4 Strip", LocalDate.of(2026, 9, 20), "SUP-003"));
        medicineList.add(new MedicineDTO((long)5,"MED-105", "Omeprazole", "Prilosec", "BN-3381", "20mg Capsules", "Antacid", 24.50, 15.00, 180, 25, "28 Caps Bottle", LocalDate.of(2027, 1, 30), "SUP-002"));
        medicineList.add(new MedicineDTO((long)6,"MED-106", "Atorvastatin", "Lipitor", "L-6671", "10mg Tablets", "Statins", 32.00, 20.50, 210, 20, "30 Tabs Pack", LocalDate.of(2027, 8, 10), "SUP-004"));
        medicineList.add(new MedicineDTO((long)7,"MED-107", "Ibuprofen", "Advil", "BN-5540", "400mg Tablets", "NSAID", 8.90, 4.50, 640, 50, "20 Tabs Strip", LocalDate.of(2026, 11, 5), "SUP-001"));
        medicineList.add(new MedicineDTO((long)8,"MED-108", "Salbutamol", "Ventolin", "V-9002", "100mcg Inhaler", "Bronchodilator", 45.00, 28.00, 95, 15, "200 Dose Unit", LocalDate.of(2027, 3, 25), "SUP-005"));
        medicineList.add(new MedicineDTO((long)9,"MED-109", "Losartan", "Cozaar", "BN-2219", "50mg Tablets", "Antihypertensive", 21.30, 13.40, 290, 30, "30 Tabs Pack", LocalDate.of(2026, 7, 14), "SUP-003"));
        medicineList.add(new MedicineDTO((long)10,"MED-110", "Azithromycin", "Zithromax", "BN-8831", "250mg Tablets", "Antibiotic", 38.00, 24.00, 145, 10, "6 Tabs Pack", LocalDate.of(2027, 6, 18), "SUP-004"));
        return medicineList;
    }

    @Override
    public List<RecentActivityDTO> getAllActivity() throws SQLException {
        List<RecentActivityDTO> activities = new ArrayList<>();

        activities.add(new RecentActivityDTO("New Stock Added (Amoxicillin - BN-22901)", LocalDate.of(2026, 2, 21),  LocalTime.of(9, 15)));
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

    @Override
    public List<SaleDTO> getAllSalseData() throws SQLException {
        List<SaleDTO> salesList = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2026, 1, 4);

// Mock data arrays for variety
        String[] items = {"MED-001", "MED-002", "MED-003", "MED-004", "MED-005"};
        String[] cats = {"Antibiotic", "Analgesic", "Antihistamine", "Antidiabetic", "Antacid"};
        double[] prices = {12.50, 5.00, 8.75, 15.20, 22.00};

        for (int i = 0; i < 100; i++) {
            int randomIndex = i % 5; // Rotates through the mock data
            LocalDate saleDate = startDate.plusDays(i);
            int qty = 5 + (i % 10); // Varied quantity between 5 and 15

            salesList.add(new SaleDTO(
                    "S-50" + (i + 1),
                    items[randomIndex],
                    cats[randomIndex],
                    prices[randomIndex],
                    qty,
                    saleDate,
                    LocalTime.of(10, 30).plusMinutes(i * 5)
            ));
        }
        return salesList;
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptionData() throws SQLException {
        return List.of();
    }


}
