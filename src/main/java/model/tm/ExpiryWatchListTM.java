package model.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ExpiryWatchListTM {
    private String batch;
    private String medCode;
    private LocalDate expiryDate;
    private double value;
}
