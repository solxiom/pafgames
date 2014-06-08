/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.repository;

import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.domain.entities.Order;

/**
 *
 * @author kavan
 */
public class OrderMongoRepository extends GenericMongoRepository<Order> implements OrderRepository {
    
}
