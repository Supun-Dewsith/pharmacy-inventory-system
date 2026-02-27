package model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerUpdateRequestDTO {
    private Long id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
}
