package service.custom;

import model.MedicineDTO;
import model.RecentActivity;
import model.RecentActivityDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface MainDashBoardService extends SuperService {
    public List<MedicineDTO> getAll() throws SQLException;
    public List<RecentActivityDTO> getAllActivity() throws SQLException;
}
