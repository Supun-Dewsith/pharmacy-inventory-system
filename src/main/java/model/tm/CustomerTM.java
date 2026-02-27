package model.tm;

import lombok.*;
import model.dto.BuyerOrderDTO;
import model.entity.BuyerOrder;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    private Long id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private List<BuyerOrderDTO> orders;
}
