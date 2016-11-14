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

    public Type type(){
        return this.type;
    }
}
