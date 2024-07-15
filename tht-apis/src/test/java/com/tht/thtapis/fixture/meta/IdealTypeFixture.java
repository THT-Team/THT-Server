package com.tht.thtapis.fixture.meta;


import com.tht.infra.idealtype.IdealTypeMapper;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;

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
