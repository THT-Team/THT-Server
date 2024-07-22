package com.tht.domain.entity.dailyfalling;

import com.tht.domain.entity.Auditable;
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
