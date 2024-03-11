package com.tht.api.app.documentation;

import com.tht.api.app.entity.enums.EnumModel;

import java.util.Arrays;

public abstract class EnumDocsUtils {

    public static String getTypesFieldList(Class<? extends EnumModel> enums) {

        return Arrays.stream(enums.getEnumConstants())
                .map(EnumModel::getValue)
                .toList()
                .toString();
    }

}
