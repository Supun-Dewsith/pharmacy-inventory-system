package service.custom;

import model.dto.MedicineDTO;
import model.dto.SuplierDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface SuplierManagementService extends SuperService {
    public List<SuplierDTO> getSuplierData() throws SQLException;
    boolean addNewSuplier(SuplierDTO suplierDTO) throws SQLException;
    boolean updateSuplier(SuplierDTO suplierDTO) throws SQLException;

}
