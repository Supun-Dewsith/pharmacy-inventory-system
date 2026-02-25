package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LowStockTM {
    private String medicineName;
    private int stock;
    private int minLevel;
    private String suplier;
}
