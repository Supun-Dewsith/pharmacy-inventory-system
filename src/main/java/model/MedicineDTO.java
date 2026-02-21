package model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedicineDTO {
    private String itemCode;
    private String batchNumber;
    private String description;
    private String category;
    private double unitPrice;
    private double buyingPrice;
    private int stock;
    private int minLevel;
    private String packSize;
    private LocalDate expiryDate;
    private String supplierId;
    private int reorderLevel;
}
