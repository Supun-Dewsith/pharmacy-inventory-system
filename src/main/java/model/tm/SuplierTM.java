package model.tm;

import lombok.*;
import model.dto.OrderDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SuplierTM {
    private Long id;
    private String name;
    private String contactPerson;
    private String phone;
    private Integer leadTime;
    private String email;
    private String status;
    private List<OrderDTO> orders;
}
