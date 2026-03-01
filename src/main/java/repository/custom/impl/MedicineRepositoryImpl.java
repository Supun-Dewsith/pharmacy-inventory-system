package repository.custom.impl;

import db.DBConnection;
import model.dto.MedicineDTO;
import model.entity.Medicine;
import repository.custom.MedicineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    @Override
    public boolean create(Medicine medicine) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO medicine (item_code, med_name, brand, batch_number, description, " +
                    "category, unit_price, buying_price, stock, min_level, pack_size, " +
                    "expiry_date, supplier_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, medicine.getItemCode());
            pstm.setString(2, medicine.getMedName());
            pstm.setString(3, medicine.getBrand());
            pstm.setString(4, medicine.getBatchNumber());
            pstm.setString(5, medicine.getDescription());
            pstm.setString(6, medicine.getCategory());
            pstm.setDouble(7, medicine.getUnitPrice());
            pstm.setDouble(8, medicine.getBuyingPrice());
            pstm.setInt(9, medicine.getStock());
            pstm.setInt(10, medicine.getMinLevel());
            pstm.setString(11, medicine.getPackSize());
            pstm.setObject(12, medicine.getExpiryDate());
            pstm.setString(13, medicine.getSupplierId());

            return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean update(Medicine medicine) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE medicine SET item_code=?, med_name=?, brand=?, batch_number=?, " +
                "description=?, category=?, unit_price=?, buying_price=?, stock=?, " +
                "min_level=?, pack_size=?, expiry_date=?, supplier_id=? WHERE id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, medicine.getItemCode());
        pstm.setString(2, medicine.getMedName());
        pstm.setString(3, medicine.getBrand());
        pstm.setString(4, medicine.getBatchNumber());
        pstm.setString(5, medicine.getDescription());
        pstm.setString(6, medicine.getCategory());
        pstm.setDouble(7, medicine.getUnitPrice());
        pstm.setDouble(8, medicine.getBuyingPrice());
        pstm.setInt(9, medicine.getStock());
        pstm.setInt(10, medicine.getMinLevel());
        pstm.setString(11, medicine.getPackSize());
        pstm.setObject(12, medicine.getExpiryDate());
        pstm.setString(13, medicine.getSupplierId());

        pstm.setLong(14, medicine.getId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        System.out.println(id);
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medicine WHERE id=?");
        preparedStatement.setLong(1,id);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public Medicine getById(Long id) throws SQLException {
        System.out.println(id);
        return null;
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM medicine";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Medicine medicine = new Medicine();

            medicine.setId(resultSet.getLong("id"));
            medicine.setItemCode(resultSet.getString("item_code"));
            medicine.setMedName(resultSet.getString("med_name"));
            medicine.setBrand(resultSet.getString("brand"));
            medicine.setBatchNumber(resultSet.getString("batch_number"));
            medicine.setDescription(resultSet.getString("description"));
            medicine.setCategory(resultSet.getString("category"));
            medicine.setUnitPrice(resultSet.getDouble("unit_price"));
            medicine.setBuyingPrice(resultSet.getDouble("buying_price"));
            medicine.setStock(resultSet.getInt("stock"));
            medicine.setMinLevel(resultSet.getInt("min_level"));
            medicine.setPackSize(resultSet.getString("pack_size"));

            medicine.setExpiryDate(resultSet.getObject("expiry_date", LocalDate.class));

            medicine.setSupplierId(resultSet.getString("supplier_id"));

            medicineList.add(medicine);
        }
        return medicineList;
    }

}
