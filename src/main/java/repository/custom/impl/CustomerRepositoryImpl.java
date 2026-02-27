package repository.custom.impl;

import model.entity.Customer;
import repository.custom.CustomerRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Customer> customerList = new ArrayList<>();

        customerList.add(new Customer((long)1, "Mr.", "Supun Dewsith", LocalDate.of(1998, 5, 15), "123 Main St, Colombo", "0771234567", "supun@example.com", new ArrayList<>()));
        customerList.add(new Customer((long)2, "Ms.", "Ishara Perera", LocalDate.of(1995, 11, 20), "45 Galle Rd, Kandy", "0719876543", "ishara@example.com", new ArrayList<>()));
        customerList.add(new Customer((long)3, "Dr.", "Amal Silva", LocalDate.of(1980, 2, 10), "88 Lake View, Negombo", "0755556666", "amal.s@clinic.lk", new ArrayList<>()));
        customerList.add(new Customer((long)4, "Mrs.", "Kavindi Gunawardena", LocalDate.of(1992, 8, 30), "12/A Temple Rd, Jaffna", "0781112222", "kavi.g@gmail.com", new ArrayList<>()));
        customerList.add(new Customer((long)5, "Mr.", "Nuwan Thilina", LocalDate.of(2000, 1, 5), "202 Orchid Apt, Malabe", "0704445555", "nuwan.t@outlook.com", new ArrayList<>()));

        return customerList;
    }
}
