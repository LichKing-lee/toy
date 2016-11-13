package com.yong.toy.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by LichKing on 2016. 11. 13..
 */
public abstract class TypeReference<T> {
    private Type type;

    public TypeReference(){
        this.type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;

        TypeReference<?> that = (TypeReference<?>) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
