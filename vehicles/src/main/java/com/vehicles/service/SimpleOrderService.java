/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.service;

import com.vehicles.domain.entities.Order;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.interfaces.OrderService;

/**
 *
 * @author kavan
 */
public class SimpleOrderService extends GenericServiceImpl<Order> implements OrderService{

    private ColorRepository colorRepo;
    public SimpleOrderService(OrderRepository repository, ColorRepository colorRepo) {
        super(repository);
        this.colorRepo = colorRepo;
    }

    @Override
    public void save(Order order) throws Exception, NullPointerException, StoarageOutOfColorException {
        if(order == null || order.getVehicle() == null){
            throw new NullPointerException("Order and order's Vehicle should not be null!");
        }
        if(colorRepo.findOneByField("name", order.getVehicle().getColor().toString()) == null){
            throw new StoarageOutOfColorException(", for color " + order.getVehicle().getColor().toString());
        }
        super.save(order);  
    }
    
    
}
