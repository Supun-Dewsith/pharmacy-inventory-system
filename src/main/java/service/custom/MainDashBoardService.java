package service.custom;

import model.dto.MedicineDTO;
import model.dto.PrescriptionDTO;
import model.dto.RecentActivityDTO;
import model.dto.SaleDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface MainDashBoardService extends SuperService {
    public List<MedicineDTO> getAll() throws SQLException;
    public List<RecentActivityDTO> getAllActivity() throws SQLException;
    public List<SaleDTO> getAllSalseData() throws SQLException;
    public List<PrescriptionDTO> getAllPrescriptionData() throws SQLException;


}
