/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import com.vehicles.domain.entities.PafGame;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kavan
 */
@Component
public class PafGameMongoRepository extends GenericMongoRepository<PafGame>
        implements PafGameRepository {

}
