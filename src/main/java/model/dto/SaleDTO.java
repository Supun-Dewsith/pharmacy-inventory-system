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
    private Long saleId;
    private Double soldPrice;
    private LocalDate date;
    private LocalTime time;
}
