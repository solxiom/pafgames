/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.domain.entities.Order;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.OrderService;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kavan
 */
public class SimpleOrderService extends GenericServiceImpl<Order> implements OrderService {

    private ColorService colorService;

    public SimpleOrderService(OrderRepository repository, ColorService colorService) {

        super(repository);
        this.colorService = colorService;
    }

    @Override
    public void save(Order order) throws Exception, NullPointerException, StoarageOutOfColorException {
        if (order == null || order.getVehicle() == null || order.getVehicle().getColor() == null) {
            throw new NullPointerException("Order and Order's Vehicle and Vehicle's Color should not be null!");
        }
        if (!colorService.isColorExists(order.getVehicle().getColor().getName())) {
            throw new StoarageOutOfColorException(", for color " + order.getVehicle().getColor().getName());
        }
        super.save(order);
        colorService.popColor(order.getVehicle().getColor().getName());
    }

    @Override
    public List<Order> findByVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return new LinkedList<Order>();
        }
        OrderRepository repo = (OrderRepository) repository;
        return repo.findByVehicle(vehicle);
    }

    @Override
    public Order findOneByVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        OrderRepository repo = (OrderRepository) repository;
        return repo.findOneByVehicle(vehicle);

    }

}
