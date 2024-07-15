package com.tht.infra.dailyfalling;

import com.tht.infra.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DailyFallingActiveInfo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    @Convert(converter = DailyFallingTypeConverter.class)
    private DailyFallingType type;

    @Column
    private String introduction;

    public static DailyFallingActiveInfo of(final LocalDateTime start, final LocalDateTime end, final DailyFallingType type, final String introduction) {

        return new DailyFallingActiveInfo(null, start, end, type, introduction);
    }
}
