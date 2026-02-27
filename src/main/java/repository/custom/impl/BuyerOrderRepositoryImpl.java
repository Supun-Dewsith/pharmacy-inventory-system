package repository.custom.impl;

import model.dto.SaleDTO;
import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Medicine;
import model.entity.SuplierOrder;
import repository.custom.BuyerOrderRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BuyerOrderRepositoryImpl implements BuyerOrderRepository {

    @Override
    public boolean create(BuyerOrder buyerOrder) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BuyerOrder buyerOrder) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public BuyerOrder getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<BuyerOrder> getAll() throws SQLException {
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
        return buyerOrders;
    }
}
