package com.tht.api.exception.custom;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityStateException extends RuntimeException {

    public EntityStateException (final String message){ super(message);}

    public static EntityStateException duplicateColumnOf(final String entity, final String column) {
        return new EntityStateException (entity + " 엔티티의 " + column + " 값이 이미 존재합니다.\n");
    }

    public static EntityStateException doNotExistOf(final String entity) {
        return new EntityStateException("해당 겂에 해당하는 " + entity + " 가(이) 존재하지 않습니다.");
    }
}
