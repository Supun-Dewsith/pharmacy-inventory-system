package service.custom;

import model.dto.MedicineDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface MedicineManagementService extends SuperService {
    public List<MedicineDTO> getAllMed() throws SQLException;

    boolean addNewMedicine(MedicineDTO medicineDTO) throws SQLException;
    boolean updateMedicine(MedicineDTO medicineDTO) throws SQLException;

    boolean deleteMed(Long id) throws SQLException;
}
