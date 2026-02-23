package service.custom;

import model.dto.MedicineDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ExpiryStockAlertsService extends SuperService {
    public List<MedicineDTO> getAllMed() throws SQLException;

}
