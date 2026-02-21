package model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RecentActivityDTO {
    private String acticity;
    private LocalDate date;
    private LocalTime time;
}
