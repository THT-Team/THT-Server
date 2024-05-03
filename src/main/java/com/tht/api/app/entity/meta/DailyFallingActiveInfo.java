package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.Auditable;
import com.tht.api.app.entity.enums.DailyFallingType;
import com.tht.api.app.entity.enums.converter.DailyFallingTypeConverter;
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
