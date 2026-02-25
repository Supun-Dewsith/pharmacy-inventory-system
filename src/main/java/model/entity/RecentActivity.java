package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecentActivity {
    private String acticity;
    private LocalDate date;
    private LocalTime time;
}
