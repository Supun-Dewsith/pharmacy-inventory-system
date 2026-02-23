package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CartDTO {
    private String code;
    private Double price;
    private int qty;
    private Double total;
}
