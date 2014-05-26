/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.service;

import com.pafgames.domain.entities.PafGame;
import java.util.List;

/**
 *
 * @author kavan
 */
public interface GameService {

    public void saveGame(PafGame game);

    public void removeGame(PafGame game);

    public List<PafGame> listAllGames();

    public PafGame findOneByField(String field, String value);

    public List<PafGame> findByField(String field, String value);
}
