package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderDetailsTM {
    private String orderid;
    private LocalDate date;
    private Double price;
}
