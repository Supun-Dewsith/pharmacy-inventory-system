package service.custom.impl;

import model.dto.MedicineDTO;
import model.entity.Medicine;
import repository.RepositoryFactroy;
import repository.custom.MedicineRepository;
import service.custom.MedicineManagementService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineManagementServiceImpl implements MedicineManagementService {
    MedicineRepository medicineRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.MEDICINE);

    @Override
    public List<MedicineDTO> getAllMed() throws SQLException {
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
    public boolean addNewMedicine(MedicineDTO medicineDTO) throws SQLException {
        return medicineRepository.create(new Medicine(
                medicineDTO.getId(),
                medicineDTO.getItemCode(),
                medicineDTO.getMedName(),
                medicineDTO.getBrand(),
                medicineDTO.getBatchNumber(),
                medicineDTO.getDescription(),
                medicineDTO.getCategory(),
                medicineDTO.getUnitPrice(),
                medicineDTO.getBuyingPrice(),
                medicineDTO.getStock(),
                medicineDTO.getMinLevel(),
                medicineDTO.getPackSize(),
                medicineDTO.getExpiryDate(),
                medicineDTO.getSupplierId()
        ));
    }

    @Override
    public boolean updateMedicine(MedicineDTO medicineDTO) throws SQLException {
        return medicineRepository.update(new Medicine(
                medicineDTO.getId(),
                medicineDTO.getItemCode(),
                medicineDTO.getMedName(),
                medicineDTO.getBrand(),
                medicineDTO.getBatchNumber(),
                medicineDTO.getDescription(),
                medicineDTO.getCategory(),
                medicineDTO.getUnitPrice(),
                medicineDTO.getBuyingPrice(),
                medicineDTO.getStock(),
                medicineDTO.getMinLevel(),
                medicineDTO.getPackSize(),
                medicineDTO.getExpiryDate(),
                medicineDTO.getSupplierId()
        ));
    }

    @Override
    public boolean deleteMed(Long id) throws SQLException {
        return medicineRepository.deleteById(id);
    }

}
