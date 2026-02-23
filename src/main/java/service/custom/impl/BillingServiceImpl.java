package service.custom.impl;

import javafx.collections.ObservableList;
import model.dto.CartDTO;
import model.dto.MedicineDTO;
import model.tm.CartTM;
import service.custom.BillingService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingServiceImpl implements BillingService {
    @Override
    public List<MedicineDTO> getAllMed() throws SQLException {
        List<MedicineDTO> medicineList = new ArrayList<>();

        medicineList.add(new MedicineDTO("MED-101", "Amoxicillin", "Amoxil", "BN-7721", "500mg Capsules", "Antibiotic", 15.50, 10.00, 450, 50, "10x10 Blister", LocalDate.of(2027, 5, 12), "SUP-001"));
        medicineList.add(new MedicineDTO("MED-102", "Paracetamol", "Panadol", "L-4409", "500mg Tablets", "Analgesic", 4.25, 2.10, 1200, 100, "1000 Bulk Pack", LocalDate.of(2026, 12, 1), "SUP-002"));
        medicineList.add(new MedicineDTO("MED-103", "Cetirizine", "Zyrtec", "BN-9902", "10mg Tablets", "Antihistamine", 12.00, 7.50, 300, 30, "30 Tabs Pack", LocalDate.of(2027, 2, 15), "SUP-001"));
        medicineList.add(new MedicineDTO("MED-104", "Metformin", "Glucophage", "TX-1142", "850mg Tablets", "Antidiabetic", 18.75, 11.20, 550, 40, "14x4 Strip", LocalDate.of(2026, 9, 20), "SUP-003"));
        medicineList.add(new MedicineDTO("MED-105", "Omeprazole", "Prilosec", "BN-3381", "20mg Capsules", "Antacid", 24.50, 15.00, 180, 25, "28 Caps Bottle", LocalDate.of(2027, 1, 30), "SUP-002"));
        medicineList.add(new MedicineDTO("MED-106", "Atorvastatin", "Lipitor", "L-6671", "10mg Tablets", "Statins", 32.00, 20.50, 210, 20, "30 Tabs Pack", LocalDate.of(2027, 8, 10), "SUP-004"));
        medicineList.add(new MedicineDTO("MED-107", "Ibuprofen", "Advil", "BN-5540", "400mg Tablets", "NSAID", 8.90, 4.50, 640, 50, "20 Tabs Strip", LocalDate.of(2026, 11, 5), "SUP-001"));
        medicineList.add(new MedicineDTO("MED-108", "Salbutamol", "Ventolin", "V-9002", "100mcg Inhaler", "Bronchodilator", 45.00, 28.00, 95, 15, "200 Dose Unit", LocalDate.of(2027, 3, 25), "SUP-005"));
        medicineList.add(new MedicineDTO("MED-109", "Losartan", "Cozaar", "BN-2219", "50mg Tablets", "Antihypertensive", 21.30, 13.40, 290, 30, "30 Tabs Pack", LocalDate.of(2026, 7, 14), "SUP-003"));
        medicineList.add(new MedicineDTO("MED-110", "Azithromycin", "Zithromax", "BN-8831", "250mg Tablets", "Antibiotic", 38.00, 24.00, 145, 10, "6 Tabs Pack", LocalDate.of(2027, 6, 18), "SUP-004"));
        return medicineList;
    }

    @Override
    public List<CartDTO> getCartData() {
        return List.of();
    }

    @Override
    public Double calculateTotal(ObservableList<CartTM> cartTMS) {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS){
            total+=cartTM.getTotal();
        }
        return total;
    }
}
