package service.custom.impl;

import model.dto.MedicineDTO;
import model.dto.PrescriptionDTO;
import model.dto.RecentActivityDTO;
import model.dto.SaleDTO;
import model.entity.Medicine;
import model.entity.RecentActivity;
import repository.RepositoryFactroy;
import repository.custom.MedicineRepository;
import repository.custom.RecentActivityRepository;
import service.custom.MainDashBoardService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainDashBoardServiceImpl implements MainDashBoardService {
    MedicineRepository medicineRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.MEDICINE);
    RecentActivityRepository recentActivityRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.RECENTACTIVITY);

    @Override
    public List<MedicineDTO> getAll() throws SQLException {
        List<MedicineDTO> medicineList = new ArrayList<>();
        List<Medicine> all = medicineRepository.getAll();
        all.forEach(medicine -> {
            medicineList.add(new MedicineDTO(
                    medicine.getId(),
                    medicine.getItemCode(),
                    medicine.getMedName(),
                    medicine.getBrand(),
                    medicine.getBatchNumber(),
                    medicine.getDescription(),
                    medicine.getCategory(),
                    medicine.getUnitPrice(),
                    medicine.getBuyingPrice(),
                    medicine.getStock(),
                    medicine.getMinLevel(),
                    medicine.getPackSize(),
                    medicine.getExpiryDate(),
                    medicine.getSupplierId()
            ));
        });
        return medicineList;
    }

    @Override
    public List<RecentActivityDTO> getAllActivity() throws SQLException {
        List<RecentActivityDTO> activities = new ArrayList<>();
        List<RecentActivity> all = recentActivityRepository.getAll();
        all.forEach(activity -> {
            activities.add(new RecentActivityDTO(
                   activity.getActicity(),
                   activity.getDate(),
                   activity.getTime()
            ));
        });
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
