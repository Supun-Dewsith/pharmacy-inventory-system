package model.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
