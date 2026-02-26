package model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuplierOrderDTO {
    private Long orderID;
    private Long suplierID;
    private LocalDate orderDate;
    private LocalDate ReceivedDate;
    private List<LotDTO> lots;
}
