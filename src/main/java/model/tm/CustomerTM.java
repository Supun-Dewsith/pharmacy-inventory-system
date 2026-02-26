package model.tm;

import model.dto.BuyerOrderDTO;
import model.entity.BuyerOrder;

import java.time.LocalDate;
import java.util.List;

public class CustomerTM {
    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private List<BuyerOrderDTO> orders;
}
