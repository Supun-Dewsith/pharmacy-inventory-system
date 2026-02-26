package model.dto;

import model.entity.BuyerOrder;

import java.time.LocalDate;
import java.util.List;

public class CustomerDTO {
    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private List<BuyerOrderDTO> orders;
}
