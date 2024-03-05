package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "talk_keyword")
public class TalkKeyword extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "talk_keyword_img_idx")
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
