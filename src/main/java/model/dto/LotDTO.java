package model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LotDTO {
    private Long LotID;
    private String lotNumber;
    private LocalDate expiryDate;
    private String qualityInspectionResults;
    private Double unitCost;
}
