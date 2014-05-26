/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pafgames.repository;

import com.pafgames.domain.entities.PafGame;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kavan
 */
@Repository
public interface PafGameRepository extends GenericRepository<PafGame> {
    
}
