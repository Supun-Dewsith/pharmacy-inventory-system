package repository.custom.impl;

import db.DBConnection;
import model.dto.LotDTO;
import model.dto.SuplierDTO;
import model.entity.*;
import repository.custom.SuplierRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuplierRepositoryImpl implements SuplierRepository {
    @Override
    public boolean create(Suplier suplier) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO supplier (name, contact_person, phone, lead_time, email, status) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,suplier.getName());
        pstm.setString(2,suplier.getContactPerson());
        pstm.setObject(3,suplier.getPhone());
        pstm.setString(4,suplier.getLeadTime().toString());
        pstm.setString(5,suplier.getEmail());
        pstm.setString(6,suplier.getStatus());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Suplier suplier) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE supplier SET name=?, contact_person=?, phone=?, lead_time=?, email=?, status=? WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,suplier.getName());
        pstm.setString(2,suplier.getContactPerson());
        pstm.setObject(3,suplier.getPhone());
        pstm.setString(4,suplier.getLeadTime().toString());
        pstm.setString(5,suplier.getEmail());
        pstm.setString(6,suplier.getStatus());
        pstm.setLong(7,suplier.getId());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE id=?");
        preparedStatement.setString(1,id.toString());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public Suplier getById(Long id) throws SQLException {
        System.out.println(id);
        return null;
    }

    @Override
    public List<Suplier> getAll() throws SQLException {
        List<Suplier> suppliers = new ArrayList<>();

        String sql_1 = "SELECT * FROM supplier";
        String sql_2 = "SELECT * FROM supplier_order WHERE supplier_id=?";
        String sql_3 = "SELECT * FROM lot WHERE order_id=?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement_1 = connection.prepareStatement(sql_1);
        ResultSet resultSet_1 = preparedStatement_1.executeQuery();

        PreparedStatement preparedStatement_2 = connection.prepareStatement(sql_2);
        PreparedStatement preparedStatement_3 = connection.prepareStatement(sql_3);

        while (resultSet_1.next()){
            Suplier customer = new Suplier(
                    Long.parseLong(resultSet_1.getString(1)),
                    resultSet_1.getString(2),
                    resultSet_1.getString(3),
                    resultSet_1.getString(4),
                    Integer.parseInt(resultSet_1.getString(5)),
                    resultSet_1.getString(6),
                    resultSet_1.getString(7),
                    null
            );

            List<SuplierOrder> suplierOrders = new ArrayList<>();
            preparedStatement_2.setString(1,customer.getId().toString());
            ResultSet resultSet_2 = preparedStatement_2.executeQuery();

            while (resultSet_2.next()){

                Date sqlDate = resultSet_2.getDate(4);
                LocalDate receivedDate = (sqlDate != null)? sqlDate.toLocalDate():null;

                SuplierOrder suplierOrder = new SuplierOrder(
                        Long.parseLong(resultSet_2.getString(1)),
                        Long.parseLong(resultSet_2.getString(2)),
                        resultSet_2.getDate(3).toLocalDate(),
                        receivedDate,
                        null
                );

                List<Lot> lots = new ArrayList<>();
                preparedStatement_3.setString(1,suplierOrder.getOrderID().toString());
                ResultSet resultSet_3 = preparedStatement_3.executeQuery();

                while (resultSet_3.next()){
                    Lot lot = new Lot(
                            Long.parseLong(resultSet_3.getString(1)),
                            Long.parseLong(resultSet_3.getString(2)),
                            resultSet_3.getString(3),
                            resultSet_3.getDate(4).toLocalDate(),
                            resultSet_3.getString(5),
                            Double.parseDouble(resultSet_3.getString(6))

                    );
                    lots.add(lot);
                }
                suplierOrder.setLots(lots);
                suplierOrders.add(suplierOrder);
            }

            customer.setOrders(suplierOrders);
            suppliers.add(customer);
        }
        return suppliers;

    }
}
