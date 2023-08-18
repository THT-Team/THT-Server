package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "daily_falling")
public class DailyFalling extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "talk_keyword_idx")
    private Integer talkKeywordIdx;

    @Column(name = "active_time_table_idx")
    private Integer activeTimeTableIdx;

    @Column(name = "talk_issue")
    private String talkIssue;

    public static DailyFalling of(int talkKeywordIdx, int activeTimeTableIdx, String talkIssue) {

        return new DailyFalling(null, talkKeywordIdx, activeTimeTableIdx, talkIssue);
    }
}
