package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrderItemTM {
    private String code;
    private String name;
    private Integer qty;
}
