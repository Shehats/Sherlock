package com.sherlock.Mycroft.controllers;

import com.sherlock.Mycroft.instance.Instance;
import com.sherlock.Mycroft.services.GenService;
import com.sherlock.Mycroft.services.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public abstract class InstanceController <T extends Instance> {
    @Autowired
    private GenService<T> service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<T> getAll() {
        return this.service.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean add(@RequestBody T obj) {
        return this.service.create(obj);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public boolean update(@RequestBody T obj) {
        return this.service.update(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@PathVariable("id") Long id, @RequestBody T obj) {
        return this.service.update(id, obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T getById (@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @RequestMapping(value = "/{name}/{value}", method = RequestMethod.GET)
    public List<T> getByName(@PathVariable("name") String key, @PathVariable("value") String value) throws NoSuchFieldException {
        return this.service.getByKey(key, value);
    }
}
