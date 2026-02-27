package repository.custom.impl;

import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Customer;
import repository.custom.CustomerRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public boolean deleteById(Long id) throws SQLException {
        System.out.println(id);
        return false;
    }

    @Override
    public Customer getById(Long id) throws SQLException {
        System.out.println(id);
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

        List<BuyerOrder> buyerOrders = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2026, 1, 4);

        // Mock data arrays for variety
        String[] items = {"MED-001", "MED-002", "MED-003", "MED-004", "MED-005"};
        String[] cats = {"Antibiotic", "Analgesic", "Antihistamine", "Antidiabetic", "Antacid"};
        double[] prices = {12.50, 5.00, 8.75, 15.20, 22.00};

        for (int i = 0; i < 100; i++) {
            int randomIndex = i % 5; // Rotates through the mock data
            LocalDate saleDate = startDate.plusDays(i);
            int qty = 5 + (i % 10); // Varied quantity between 5 and 15

            List<BuyerOrderItem> cart = new ArrayList<>();

            cart.add(new BuyerOrderItem((long)2, "MED-AM-042", 1, 12.75));
            cart.add(new BuyerOrderItem((long)3, "MED-VH-019",3, 24.00));
            cart.add(new BuyerOrderItem((long)4, "MED-AS-008", 5, 21.00));
            cart.add(new BuyerOrderItem((long)4, "MED-CP-102", 1, 15.00));
            cart.add(new BuyerOrderItem((long)8, "MED-AM-042", 1, 12.75));
            cart.add(new BuyerOrderItem((long)3, "MED-VH-019",3, 24.00));
            cart.add(new BuyerOrderItem((long)1, "MED-AS-008", 5, 21.00));
            cart.add(new BuyerOrderItem((long)4, "MED-CP-102", 1, 15.00));

            buyerOrders.add(new BuyerOrder(
                    (long)i,
                    items[randomIndex],
                    prices[randomIndex],
                    saleDate,
                    LocalTime.of(10, 30).plusMinutes(i * 5),
                    cart
            ));
        }

        customerList.forEach(customer -> {
            customer.setOrders(buyerOrders);
        });
        return customerList;
    }
}
