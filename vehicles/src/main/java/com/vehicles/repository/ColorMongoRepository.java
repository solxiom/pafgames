/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.repository;

import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.domain.entities.Color;

/**
 *
 * @author kavan
 */
public class ColorMongoRepository extends GenericMongoRepository<Color> implements ColorRepository{
    
}
