package repository.custom.impl;

import model.entity.Customer;
import repository.custom.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) throws SQLException {
        System.out.println(customer.toString());
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        System.out.println(customer.toString());
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public Customer getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }
}
