package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChartBarDTO {
    private String itemName;
    private int quantitySold;
}