package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private Long id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private List<BuyerOrder> orders;
}
