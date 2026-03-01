package model.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Lot {
    private Long LotID;
    private Long orderId;
    private String lotNumber;
    private LocalDate expiryDate;
    private String qualityInspectionResults;
    private Double unitCost;
}
