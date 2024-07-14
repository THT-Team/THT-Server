package com.tht.thtapis.facade.idealtype.response;


import com.tht.infra.idealtype.IdealType;
import com.tht.infra.idealtype.IdealTypeMapper;

public record IdealTypeResponse(
    Integer idx,
    String name,
    String emojiCode) {

    public static IdealTypeResponse of(final IdealTypeMapper mapper) {
        return new IdealTypeResponse(
            mapper.idx(),
            mapper.name(),
            mapper.emojiCode()
        );
    }

    public static IdealTypeResponse of(final IdealType mapper) {
        return new IdealTypeResponse(
            mapper.getIdx(),
            mapper.getName(),
            mapper.getEmojiCode()
        );
    }

}
