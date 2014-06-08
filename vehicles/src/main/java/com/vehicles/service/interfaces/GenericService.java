/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service.interfaces;

import com.vehicles.repository.interfaces.GenericRepository;
import java.util.List;

/**
 *
 * @author kavan
 */
public interface GenericService<T> {

    public void save(T entity);

    public void remove(T entity);

    public T findOneByField(String key, String value);

    public List<T> findAll();
    
}
