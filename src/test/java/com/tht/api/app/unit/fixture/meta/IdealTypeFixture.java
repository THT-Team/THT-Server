package com.tht.api.app.unit.fixture.meta;

import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.repository.mapper.IdealTypeMapper;

public class IdealTypeFixture {

    private static final Integer idx = 1;
    private static final String name = "멋진";
    private static final String emojiCode = "#2425fa";

    public static IdealTypeResponse responseMake() {
        return new IdealTypeResponse(idx, name, emojiCode);
    }


    public static IdealTypeMapper mapperMake() {
        return new IdealTypeMapper(idx, name, emojiCode);
    }
}
