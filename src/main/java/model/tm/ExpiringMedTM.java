package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExpiringMedTM {
    private String medCode;
    private String medName;
    private String brand;
    private String Category;
    private Double price;
    private LocalDate expiryDate;
}
