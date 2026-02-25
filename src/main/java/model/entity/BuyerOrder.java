package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyerOrder {
    private String Id;
    private String Code;
    private String category;
    private double totalPrice;
    private LocalDate date;
    private LocalTime time;
    private List<Medicine> cart;
}
