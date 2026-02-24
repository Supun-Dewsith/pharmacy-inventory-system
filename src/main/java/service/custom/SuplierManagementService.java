package service.custom;

import model.dto.MedicineDTO;
import model.dto.SuplierDTO;
import service.SuperService;

import java.util.List;

public interface SuplierManagementService extends SuperService {
    public List<SuplierDTO> getSuplierData();
    boolean addNewSuplier(SuplierDTO suplierDTO);
    boolean updateSuplier(SuplierDTO suplierDTO);

}
