package com.sherlock.Mycroft.services;

import com.sherlock.Mycroft.instance.Instance;

import java.util.List;

public interface InstanceService <T extends Instance> {
    List<T> getAll();
    List<T> getByKey(String key, Object val) throws NoSuchFieldException;
    T getById(Long id);
    boolean update(Long id, T target);
    boolean update(T target);
    boolean delete(Long id);
    boolean create(T target);
}
