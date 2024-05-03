package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.Auditable;
import com.tht.api.app.entity.enums.DailyFallingType;
import com.tht.api.app.entity.enums.converter.DailyFallingTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DailyFalling extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Integer talkKeywordIdx;

    @Column
    private Integer activeTimeTableIdx;

    @Column
    private String talkIssue;

    public static DailyFalling of(int talkKeywordIdx, int activeTimeTableIdx, String talkIssue) {

        return new DailyFalling(null, talkKeywordIdx, activeTimeTableIdx, talkIssue);
    }
}
