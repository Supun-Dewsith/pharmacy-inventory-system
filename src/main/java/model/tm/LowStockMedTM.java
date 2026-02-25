package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LowStockMedTM {
    private String medCode;
    private String medName;
    private String brand;
    private String Category;
    private Integer minStock;
    private Integer stock;
}
