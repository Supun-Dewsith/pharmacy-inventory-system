package service.custom.impl;

import mapper.CustomertoDTOMapper;
import model.dto.BuyerOrderDTO;
import model.dto.CustomerDTO;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import model.entity.Customer;
import repository.RepositoryFactroy;
import repository.custom.CustomerRepository;
import service.custom.CustomerManagementService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagementServiceImpl implements CustomerManagementService {
    CustomerRepository customerRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.CUSTOMER);
    CustomertoDTOMapper customertoDTOMapper = CustomertoDTOMapper.INSTANCE;

    @Override
    public List<CustomerDTO> getCustomerData() throws SQLException {
        List<Customer> all = customerRepository.getAll();

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        all.forEach(customer -> {
            customerDTOS.add(customertoDTOMapper.toDTO(customer));
                }
        );
        return customerDTOS;
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
