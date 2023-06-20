package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "daily_falling")
public class DailyFalling extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "talk_keyword_idx")
    private Integer talkKeywordIdx;

    @Column(name = "talk_issue")
    private String talkIssue;

    @Column(name = "active_day")
    private LocalDate activeDay;
}
