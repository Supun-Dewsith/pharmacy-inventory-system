package repository.custom.impl;

import db.DBConnection;
import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Customer;
import repository.custom.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer (title, name, dob, address, phone, email) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,customer.getTitle());
        pstm.setString(2,customer.getName());
        pstm.setObject(3,customer.getDob());
        pstm.setString(4,customer.getAddress());
        pstm.setString(5,customer.getPhone());
        pstm.setString(6,customer.getEmail());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET title=?, name=?, dob=?, address=?, phone=?, email=? WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,customer.getTitle());
        pstm.setString(2,customer.getName());
        pstm.setObject(3,customer.getDob());
        pstm.setString(4,customer.getAddress());
        pstm.setString(5,customer.getPhone());
        pstm.setString(6,customer.getEmail());
        pstm.setLong(7,customer.getId());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id=?");
        preparedStatement.setString(1,id.toString());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public Customer getById(Long id) throws SQLException {
        return new Customer();
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        String sql_1 = "SELECT * FROM customer";
        String sql_2 = "SELECT * FROM buyerorder WHERE cust_id=?";
        String sql_3 = "SELECT * FROM buyerorderItem WHERE order_id=?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement_1 = connection.prepareStatement(sql_1);
        ResultSet resultSet_1 = preparedStatement_1.executeQuery();

        PreparedStatement preparedStatement_2 = connection.prepareStatement(sql_2);
        PreparedStatement preparedStatement_3 = connection.prepareStatement(sql_3);



        while (resultSet_1.next()){
            Customer customer = new Customer(
                    Long.parseLong(resultSet_1.getString(1)),
                    resultSet_1.getString(2),
                    resultSet_1.getString(3),
                    resultSet_1.getDate(4).toLocalDate(),
                    resultSet_1.getString(5),
                    resultSet_1.getString(6),
                    resultSet_1.getString(7),
                    null
            );

            List<BuyerOrder> buyerOrders = new ArrayList<>();
            preparedStatement_2.setString(1,customer.getId().toString());
            ResultSet resultSet_2 = preparedStatement_2.executeQuery();

            while (resultSet_2.next()){
                BuyerOrder buyerOrder = new BuyerOrder(
                        Long.parseLong(resultSet_2.getString(1)),
                        Long.parseLong(resultSet_2.getString(2)),
                        resultSet_2.getString(3),
                        Double.parseDouble(resultSet_2.getString(4)),
                        resultSet_2.getDate(5).toLocalDate(),
                        resultSet_2.getTime(6).toLocalTime(),
                        null
                );

                List<BuyerOrderItem> cart = new ArrayList<>();
                preparedStatement_3.setString(1,buyerOrder.getId().toString());
                ResultSet resultSet_3 = preparedStatement_3.executeQuery();

                while (resultSet_3.next()){
                    BuyerOrderItem buyerOrderItem = new BuyerOrderItem(
                            Long.parseLong(resultSet_3.getString(1)),
                            Long.parseLong(resultSet_3.getString(2)),
                            Long.parseLong(resultSet_3.getString(3)),
                            resultSet_3.getString(4),
                            Integer.parseInt(resultSet_3.getString(5)),
                            Double.parseDouble(resultSet_3.getString(6))

                            );
                    cart.add(buyerOrderItem);
                }
                buyerOrder.setCart(cart);
                buyerOrders.add(buyerOrder);
            }

            customer.setOrders(buyerOrders);
            customerList.add(customer);
        }

        return customerList;

    }
}
