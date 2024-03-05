package com.tht.api.app.facade.idealtype.response;

import com.tht.api.app.repository.mapper.IdealTypeMapper;

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

}
