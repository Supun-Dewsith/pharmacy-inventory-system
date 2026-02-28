package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderDetailsTM {
    private Long orderId;
    private String orderCode;
    private LocalDate date;
    private Double price;
}
