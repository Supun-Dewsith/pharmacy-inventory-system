package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChartAreaDTO {
    private String period;
    private double totalSales;
}
