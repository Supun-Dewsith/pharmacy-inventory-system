package model.tm;

import lombok.*;
import model.dto.LotDTO;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DeleveryDetailsTM {
    private Long orderId;
    private LocalDate orderDate;
    private LocalDate receivedDate;
    private List<LotDTO> lotDTOS;

}
