package model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderItem {
    private Long medId;
    private String medCode;
    //private String category;
    //private Double price;
    private Integer qty;
    private Double total;
}
