package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FinancialTM {
    private String batchNumber;
    private Double unitCost;
    private Double totalCostIncrease;
}
