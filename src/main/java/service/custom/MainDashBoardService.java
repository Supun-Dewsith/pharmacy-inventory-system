package service.custom;

import model.dto.*;
import model.entity.BuyerOrder;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface MainDashBoardService extends SuperService {
    public List<MedicineDTO> getAll() throws SQLException;
    public List<RecentActivityDTO> getAllActivity() throws SQLException;
    public List<BuyerOrderDTO> getAllBuyerOrders() throws SQLException;
    public List<PrescriptionDTO> getAllPrescriptionData() throws SQLException;


}
