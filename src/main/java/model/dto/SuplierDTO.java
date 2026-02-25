package model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuplierDTO {
    private Long id;
    private String name;
    private String contactPerson;
    private String phone;
    private Integer leadTime;
    private String email;
    private String status;
    private List<OrderDTO> orders;

}
