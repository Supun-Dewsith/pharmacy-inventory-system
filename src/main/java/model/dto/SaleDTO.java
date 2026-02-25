package model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SaleDTO {
    private String saleId;
    private String itemCode;
    private String category;
    private double soldPrice;
    private int soldQty;
    private LocalDate date;
    private LocalTime time;
}
