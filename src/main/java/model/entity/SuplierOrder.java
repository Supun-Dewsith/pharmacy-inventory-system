package model.entity;

import lombok.*;
import model.dto.LotDTO;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuplierOrder {
    private Long orderID;
    private Long suplierID;
    private LocalDate orderDate;
    private LocalDate ReceivedDate;
    private List<Lot> lots;
}
