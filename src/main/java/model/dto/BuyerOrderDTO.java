package model.dto;

import lombok.*;
import model.entity.BuyerOrderItem;
import model.entity.Medicine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderDTO {
    private Long Id;
    private String code;
    private double totalPrice;
    private LocalDate date;
    private LocalTime time;
    private List<BuyerOrderItemDTO> cart;
}
