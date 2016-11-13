package com.yong.toy.supertypetoken;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by LichKing on 2016. 11. 13..
 */
public class TypeReferenceTest {
    private TypeSafetyMap map;

    @Before
    public void setUp(){
        this.map = new TypeSafetyMap();
    }

    @Test
    public void 단일_타입파라미터_테스트(){
        this.map.put(new TypeReference<Integer>() {}, 1);

        Integer num = this.map.get(new TypeReference<Integer>() {});

        assertThat(num, is(1));
    }

    @Test
    public void 중첩_타입파라미터_테스트(){
        this.map.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3, 4, 5));
        this.map.put(new TypeReference<List<String>>() {}, Arrays.asList("a", "b", "c"));

        List<Integer> list1 = this.map.get(new TypeReference<List<Integer>>() {});
        List<String> list2 = this.map.get(new TypeReference<List<String>>() {});

        assertThat(list1, is(Arrays.asList(1, 2, 3, 4, 5)));
        assertThat(list2, is(Arrays.asList("a", "b", "c")));
    }

    @Test
    public void 이런것도_될까(){
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(5);
        set2.add(15);

        Map<Set<Integer>, Object> map1 = new HashMap<>();
        Map<Set<Integer>, Object> map2 = new HashMap<>();
        map1.put(set1, 10);
        map2.put(set2, 10);
        this.map.put(new TypeReference<List<Map<Set<Integer>, Object>>>() {}, Arrays.asList(map1, map2));

        List<Map<Set<Integer>, Object>> list = this.map.get(new TypeReference<List<Map<Set<Integer>, Object>>>() {});

        assertThat(list, is(Arrays.asList(map1, map2)));
        assertThat(list.toString(), is("[{[5]=10}, {[15]=10}]"));
    }
}
