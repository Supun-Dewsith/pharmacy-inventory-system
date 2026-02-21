package model.tm;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RecentAcitvityLogTM {
    private String acticity;
    private LocalDate date;
    private LocalTime time;
}
