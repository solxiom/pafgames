/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.service;

import com.vehicles.service.interfaces.ColorService;
import com.vehicles.domain.entities.Color;
import com.vehicles.repository.interfaces.ColorRepository;

/**
 *
 * @author kavan
 */
public class SimpleColorService extends GenericServiceImpl<Color> implements ColorService{

    public SimpleColorService(ColorRepository repository) {
        super(repository);
    }
    
}
