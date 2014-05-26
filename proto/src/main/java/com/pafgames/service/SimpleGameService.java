/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.service;

import com.pafgames.domain.entities.PafGame;
import com.pafgames.repository.PafGameRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author kavan
 */
//@Service
public class SimpleGameService implements GameService {

    PafGameRepository repo;

    public SimpleGameService(PafGameRepository repo) {
        this.repo = repo;
    }

    @Override
    public void saveGame(PafGame game) {
        repo.save(game);
    }

    @Override
    public void removeGame(PafGame game) {
        repo.remove(game);
    }

    @Override
    public List<PafGame> listAllGames() {
        return repo.findAll();
    }

    @Override
    public PafGame findOneByField(String field, String value) {
        return repo.findOneByField(field, value);
    }

    @Override
    public List<PafGame> findByField(String field, String value) {
        return repo.findByField(field, value);
    }

}
