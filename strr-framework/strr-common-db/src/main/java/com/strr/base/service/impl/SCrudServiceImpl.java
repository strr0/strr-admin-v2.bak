package com.strr.base.service.impl;

import com.strr.base.mapper.SCrudMapper;
import com.strr.base.model.Page;
import com.strr.base.model.Pageable;
import com.strr.base.service.SCrudService;

import java.io.Serializable;

public abstract class SCrudServiceImpl<T, ID extends Serializable> implements SCrudService<T, ID> {
    protected abstract SCrudMapper<T, ID> getMapper();

    @Override
    public Page<T> page(T param, Pageable pageable) {
        return getMapper().page(param, pageable);
    }

    @Override
    public int save(T entity) {
        return getMapper().save(entity);
    }

    @Override
    public int update(T entity) {
        return getMapper().update(entity);
    }

    @Override
    public int remove(ID id) {
        return getMapper().remove(id);
    }

    @Override
    public T get(ID id) {
        return getMapper().get(id);
    }
}
