/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import java.util.List;

/**
 *
 * @author kavan
 */
public interface GenericRepository<T> {

    public T findOneByField(String field, String value);

    public List<T> findByField(String field, String value);

    public List<T> findAll();

    public void save(T entity);

    public void remove(T entity);
}
