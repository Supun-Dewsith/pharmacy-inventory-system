package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MedAndQualityTM {
    private String lotNumber;
    private LocalDate expiryDate;
    private String qualityInspectionStatus;
}
