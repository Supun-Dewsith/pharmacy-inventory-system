package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderItemDTO {
    private Long medId;
    private String medCode;
    //private String medName;
    //private String category;
    //private Double price;
    private Integer qty;
    private Double total;
}
