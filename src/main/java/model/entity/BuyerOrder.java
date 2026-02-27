package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrder {
    private Long Id;
    private String code;
    private Double totalPrice;
    private LocalDate date;
    private LocalTime time;
    private List<BuyerOrderItem> cart;
}
