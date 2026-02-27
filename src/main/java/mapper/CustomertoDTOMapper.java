package mapper;

import model.dto.BuyerOrderDTO;
import model.dto.BuyerOrderItemDTO;
import model.dto.CustomerDTO;
import model.entity.BuyerOrder;
import model.entity.BuyerOrderItem;
import model.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomertoDTOMapper {
    public static final CustomertoDTOMapper INSTANCE = new CustomertoDTOMapper();

    private CustomertoDTOMapper(){}

    public CustomerDTO toDTO(Customer customer){
        if(customer==null){
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setTitle(customer.getTitle());
        customerDTO.setName(customer.getName());
        customerDTO.setDob(customer.getDob());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());

        if(customer.getOrders()!=null){
            customerDTO.setOrders(customer.getOrders().stream()
                    .map(this::toOrderDto)
                    .collect(Collectors.toList()));
        }
        return customerDTO;
    }

    public BuyerOrderDTO toOrderDto(BuyerOrder buyerOrder){

        BuyerOrderDTO buyerOrderDTO = new BuyerOrderDTO();
        buyerOrderDTO.setId(buyerOrder.getId());
        buyerOrderDTO.setCode(buyerOrder.getCode());
        buyerOrderDTO.setTotalPrice(buyerOrder.getTotalPrice());
        buyerOrderDTO.setDate(buyerOrder.getDate());
        buyerOrderDTO.setTime(buyerOrder.getTime());

        if(buyerOrder.getCart()!=null){
            buyerOrderDTO.setCart(buyerOrder.getCart().stream()
                    .map(this::toItemDTO)
                    .collect(Collectors.toList()));
        }
        return buyerOrderDTO;
    }

    public BuyerOrderItemDTO toItemDTO(BuyerOrderItem buyerOrderItem){
        BuyerOrderItemDTO buyerOrderItemDTO = new BuyerOrderItemDTO();
        buyerOrderItemDTO.setMedId(buyerOrderItem.getMedId());
        buyerOrderItemDTO.setMedCode(buyerOrderItem.getMedCode());
        buyerOrderItemDTO.setCategory(buyerOrderItem.getCategory());
        buyerOrderItemDTO.setPrice(buyerOrderItem.getPrice());
        buyerOrderItemDTO.setQty(buyerOrderItem.getQty());
        buyerOrderItemDTO.setTotal(buyerOrderItem.getTotal());

        return buyerOrderItemDTO;
    }
}
