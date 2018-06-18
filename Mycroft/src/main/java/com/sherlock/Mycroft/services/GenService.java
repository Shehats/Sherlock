package com.sherlock.Mycroft.services;

import com.sherlock.Mycroft.exceptions.FieldException;
import com.sherlock.Mycroft.instance.Instance;
import com.sherlock.Mycroft.repositories.InstanceRepository;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public class GenService <T extends Instance> implements InstanceService<T>{
    private InstanceRepository<T> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public List<T> getByKey(String key, Object val) throws NoSuchFieldException {
        Class<T> C = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        if (Arrays.stream(C.getFields()).filter(x-> x.getName() == key).toArray().length == 0)
            throw  new FieldException();
        return repository.findByKey(key, val);
    }

    @Override
    public T getById(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, T target) {
        return false;
    }

    @Override
    public boolean update(T target) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(T target) {
        return false;
    }
}
