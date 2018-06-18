package com.sherlock.Mycroft.repositories;

import com.sherlock.Mycroft.instance.Instance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public interface InstanceRepository <T extends Instance> extends JpaRepository<T, Long> {
    default List<T> findByKey(String Filterkey, Object val) throws NoSuchFieldException {
        Class<T> C = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        Field key = C.getField(Filterkey);
        return this.findAll().stream().filter(x -> {
            try {
                return key.get(x).equals(val);
            } catch (IllegalAccessException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }
}
