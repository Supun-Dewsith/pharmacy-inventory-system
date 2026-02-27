package service.custom.impl;

import model.dto.CustomerDTO;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import service.custom.CustomerManagementService;

import java.sql.SQLException;
import java.util.List;

public class CustomerManagementServiceImpl implements CustomerManagementService {

    @Override
    public List<CustomerDTO> getCustomerData() throws SQLException {
        return List.of();
    }

    @Override
    public boolean addNewCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) throws SQLException {
        return false;
    }
}
