package service.custom;

import javafx.collections.ObservableList;
import model.dto.CartDTO;
import model.dto.MedicineDTO;
import model.tm.CartTM;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BillingService extends SuperService {
    public List<MedicineDTO> getAllMed() throws SQLException;
    List<CartDTO> getCartData();
    Double calculateTotal(ObservableList<CartTM> cartTMS);

}
