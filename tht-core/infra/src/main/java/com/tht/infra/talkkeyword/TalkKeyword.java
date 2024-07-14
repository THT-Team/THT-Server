package com.tht.infra.talkkeyword;

import com.tht.infra.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class TalkKeyword extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private Integer talkKeywordImgIdx;

    @Column
    private String keyword;

    private TalkKeyword(Integer idx, Integer talkKeywordImgIdx, String keyword) {
        this.idx = idx;
        this.talkKeywordImgIdx = talkKeywordImgIdx;
        this.keyword = keyword;
    }

    public static TalkKeyword of(final int talkKeywordImgIdx, final String keyword) {
        return new TalkKeyword(null, talkKeywordImgIdx, keyword);
    }
}
