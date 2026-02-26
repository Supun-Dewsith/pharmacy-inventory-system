package service.custom.impl;

import model.dto.*;
import model.entity.BuyerOrder;
import model.entity.Medicine;
import model.entity.RecentActivity;
import model.entity.SuplierOrder;
import repository.RepositoryFactroy;
import repository.custom.BuyerOrderRepository;
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
    BuyerOrderRepository buyerOrderRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.BUYERORDER);

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
    public List<BuyerOrderDTO> getAllBuyerOrders() throws SQLException {
        List<BuyerOrder> all = buyerOrderRepository.getAll();
        List<BuyerOrderDTO> byerOrderDTOS = new ArrayList<>();
        all.forEach(buyerOrder -> {
            byerOrderDTOS.add(new BuyerOrderDTO(
                    buyerOrder.getCode(),
                    buyerOrder.getTotalPrice(),
                    buyerOrder.getDate(),
                    buyerOrder.getTime(),
                    buyerOrder.getCart()
            ));
        });
        return byerOrderDTOS;
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptionData() throws SQLException {
        return List.of();
    }


}
