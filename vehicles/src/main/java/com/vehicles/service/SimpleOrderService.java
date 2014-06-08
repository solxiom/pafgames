/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.service;

import com.vehicles.domain.entities.Order;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.interfaces.OrderService;

/**
 *
 * @author kavan
 */
public class SimpleOrderService extends GenericServiceImpl<Order> implements OrderService{

    public SimpleOrderService(OrderRepository repository) {
        super(repository);
    }
    
}
