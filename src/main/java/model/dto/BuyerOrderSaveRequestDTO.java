package model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderSaveRequestDTO {
    private Long custId;
    private String code;
    private double totalPrice;
    private LocalDate date;
    private LocalTime time;
    private List<BuyerOrderItemDTO> cart;
}
