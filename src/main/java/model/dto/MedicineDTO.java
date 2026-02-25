package model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedicineDTO {
    private Long Id;
    private String itemCode;
    private String medName;
    private String brand;
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
}
