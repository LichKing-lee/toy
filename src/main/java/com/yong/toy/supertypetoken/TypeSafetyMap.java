package com.yong.toy.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LichKing on 2016. 11. 13..
 */
public class TypeSafetyMap {
    private Map<Type, Object> map;

    {
        map = new HashMap<>();
    }

    public <T> void put(TypeReference<T> typeReference, T t){
        this.map.put(typeReference.type(), t);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(TypeReference<T> typeReference){
        Type type = ((ParameterizedType)typeReference.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class<T> clazz;

        if(type instanceof ParameterizedType){
            clazz = (Class<T>)((ParameterizedType)type).getRawType();
        }else{
            clazz = (Class<T>) type;
        }

        return clazz.cast(map.get(typeReference.type()));
    }
}
