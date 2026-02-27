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
        return customerRepository.create(new Customer(
                null,
                customerSaveRequestDTO.getTitle(),
                customerSaveRequestDTO.getName(),
                customerSaveRequestDTO.getDob(),
                customerSaveRequestDTO.getAddress(),
                customerSaveRequestDTO.getPhone(),
                customerSaveRequestDTO.getEmail(),
                null
        ));
    }

    @Override
    public boolean updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) throws SQLException {
        return customerRepository.create(new Customer(
                customerUpdateRequestDTO.getId(),
                customerUpdateRequestDTO.getTitle(),
                customerUpdateRequestDTO.getName(),
                customerUpdateRequestDTO.getDob(),
                customerUpdateRequestDTO.getAddress(),
                customerUpdateRequestDTO.getPhone(),
                customerUpdateRequestDTO.getEmail(),
                null
        ));
    }

    @Override
    public boolean deleteCustomer(Long id) throws SQLException {
        System.out.println(id);
        return false;
    }
}
