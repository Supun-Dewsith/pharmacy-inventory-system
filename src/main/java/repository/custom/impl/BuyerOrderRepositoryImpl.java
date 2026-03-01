package repository.custom.impl;

import db.DBConnection;
import model.dto.SaleDTO;
import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Medicine;
import model.entity.SuplierOrder;
import repository.custom.BuyerOrderRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BuyerOrderRepositoryImpl implements BuyerOrderRepository {

    @Override
    public boolean create(BuyerOrder buyerOrder) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO buyerorder (cust_id, order_code, total_price, order_date, order_time) VALUES(?,?,?,?,?)";
        String sql_2 = "INSERT INTO buyerorderitem (order_id, med_id, med_code, qty, total_price) VALUES(?,?,?,?,?)";

        //get generated orderId
        PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);


        pstm.setLong(1, buyerOrder.getCustId());
        pstm.setString(2, buyerOrder.getCode());
        pstm.setDouble(3, buyerOrder.getTotalPrice());
        pstm.setObject(4, buyerOrder.getDate());
        pstm.setTime(5, Time.valueOf(buyerOrder.getTime()));


        if(pstm.executeUpdate()>0) {
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                long newOrderId = generatedKeys.getLong(1);

                for (BuyerOrderItem buyerOrderItem : buyerOrder.getCart()) {
                    try {
                        PreparedStatement pstm_2 = connection.prepareStatement(sql_2);
                        pstm_2.setLong(1, newOrderId);
                        pstm_2.setLong(2, buyerOrderItem.getMedId());
                        pstm_2.setString(3, buyerOrderItem.getMedCode());
                        pstm_2.setInt(4, buyerOrderItem.getQty());
                        pstm_2.setDouble(5, buyerOrderItem.getTotal());
                        if(!(pstm_2.executeUpdate()>0)){
                            return false;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return true;
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
        Connection connection = DBConnection.getInstance().getConnection();
        //String sql = "SELECT * FROM buyerorder WHERE order_date<DATE_SUB(CURDATE(), INTERVAL 100 DAY)";
        String sql = "SELECT * FROM buyerorder";
        String sql_2 = "SELECT * FROM buyerorderItem WHERE order_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        PreparedStatement preparedStatement_2 = connection.prepareStatement(sql_2);

        ResultSet resultSet = preparedStatement.executeQuery(sql);

        while (resultSet.next()){
            BuyerOrder buyerOrder = new BuyerOrder(
                    Long.parseLong(resultSet.getString(1)),
                    Long.parseLong(resultSet.getString(2)),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(4)),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    null
            );


            List<BuyerOrderItem> cart = new ArrayList<>();
            preparedStatement_2.setString(1,buyerOrder.getId().toString());
            ResultSet resultSet_3 = preparedStatement_2.executeQuery();

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

        return buyerOrders;
    }
}
