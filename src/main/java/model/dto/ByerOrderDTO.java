package model.dto;

import lombok.*;
import model.entity.Medicine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ByerOrderDTO {
    private String code;
    private double totalPrice;
    private LocalDate date;
    private LocalTime time;
    private List<Medicine> cart;
}
