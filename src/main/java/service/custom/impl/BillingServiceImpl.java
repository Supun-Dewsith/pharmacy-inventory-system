package service.custom.impl;

import javafx.collections.ObservableList;
import model.dto.BuyerOrderItemDTO;
import model.dto.BuyerOrderSaveRequestDTO;
import model.dto.CartDTO;
import model.dto.MedicineDTO;
import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Medicine;
import model.tm.CartTM;
import repository.RepositoryFactroy;
import repository.custom.BuyerOrderRepository;
import repository.custom.MedicineRepository;
import service.custom.BillingService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingServiceImpl implements BillingService {
    MedicineRepository medicineRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.MEDICINE);
    BuyerOrderRepository buyerOrderRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.BUYERORDER);

    @Override
    public List<MedicineDTO> getAllMed() throws SQLException {
        List<Medicine> all = medicineRepository.getAll();
        List<MedicineDTO> medicineList = new ArrayList<>();
        all.forEach(medicine -> {
            medicineList.add(new MedicineDTO(
                    medicine.getId(),
                    medicine.getItemCode(),
                    medicine.getMedName(),
                    medicine.getBrand(),
                    medicine.getBatchNumber(),
                    medicine.getDescription(),
                    medicine.getCategory(),
                    medicine.getUnitPrice(),
                    medicine.getBuyingPrice(),
                    medicine.getStock(),
                    medicine.getMinLevel(),
                    medicine.getPackSize(),
                    medicine.getExpiryDate(),
                    medicine.getSupplierId()
            ));
        });
        return medicineList;
    }

    @Override
    public List<CartDTO> getCartData() {
        return List.of();
    }

    @Override
    public Double calculateTotal(ObservableList<CartTM> cartTMS) {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS){
            total+=cartTM.getTotal();
        }
        return total;
    }

    @Override
    public boolean saveOrder(BuyerOrderSaveRequestDTO buyerOrderSaveRequestDTO) throws SQLException {
        BuyerOrder buyerOrder = new BuyerOrder(
                null,
                buyerOrderSaveRequestDTO.getCustId(),
                buyerOrderSaveRequestDTO.getCode(),
                buyerOrderSaveRequestDTO.getTotalPrice(),
                buyerOrderSaveRequestDTO.getDate(),
                buyerOrderSaveRequestDTO.getTime(),
                null
        );

        ArrayList<BuyerOrderItem> buyerOrderItems = new ArrayList<>();
        buyerOrderSaveRequestDTO.getCart().forEach(buyerOrderItemDTO -> {
            buyerOrderItems.add(new BuyerOrderItem(
                    null,
                    null,
                    buyerOrderItemDTO.getMedId(),
                    buyerOrderItemDTO.getMedCode(),
                    buyerOrderItemDTO.getQty(),
                    buyerOrderItemDTO.getTotal()
            ));
        });
        buyerOrder.setCart(buyerOrderItems);

        return buyerOrderRepository.create(buyerOrder);
    }
}
