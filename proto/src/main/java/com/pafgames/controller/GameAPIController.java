/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.controller;

import com.pafgames.domain.entities.PafGame;
import com.pafgames.repository.PafGameMongoRepository;
import com.pafgames.repository.PafGameRepository;
import com.pafgames.service.GameService;
import com.pafgames.service.SimpleGameService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kavan
 */
@Controller
@RequestMapping("/api/game")
public class GameAPIController {

    PafGameRepository repo = new PafGameMongoRepository();
    GameService service = new SimpleGameService(repo);

    @RequestMapping(value = "/{gameName}")
    public @ResponseBody
    List<PafGame> findGame(HttpServletResponse response,@PathVariable("gameName") String gameName) {        
        return service.findByField("name", gameName);
    }

    @RequestMapping(value = "/list")
    public @ResponseBody
    List<PafGame> listGames(HttpServletResponse response) {

        List<PafGame> games = service.listAllGames();
        return games;
    }
}
