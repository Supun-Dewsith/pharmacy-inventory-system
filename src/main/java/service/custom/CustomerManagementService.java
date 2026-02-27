package service.custom;

import model.dto.CustomerDTO;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import model.dto.SuplierDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerManagementService extends SuperService {
    public List<CustomerDTO> getCustomerData() throws SQLException;
    boolean addNewCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) throws SQLException;
    boolean updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) throws SQLException;

}
