/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.controller;

import com.vehicles.domain.entities.PafGame;
import com.vehicles.repository.PafGameMongoRepository;
import com.vehicles.repository.PafGameRepository;
import com.vehicles.service.interfaces.GameService;
import com.vehicles.service.SimpleGameService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kavan
 */
@Controller
@RequestMapping("/api2/game")
public class GameAPIController {

    PafGameRepository repo = new PafGameMongoRepository();
    GameService service = new SimpleGameService(repo);

    @RequestMapping(value = "/{gameName}")
    public @ResponseBody
    List<PafGame> findGame(HttpServletResponse response,@PathVariable("gameName") String gameName) {        
        return service.findByField("name", gameName);
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public void addGame(HttpServletResponse response,@RequestBody PafGame game) {        
        service.saveGame(game);
        response.setStatus(200);
    }

    @RequestMapping(value = "/list")
    public @ResponseBody
    List<PafGame> listGames(HttpServletResponse response) {

        List<PafGame> games = service.listAllGames();
        return games;
    }

   @RequestMapping(value = "/type/{typeName}")
    public @ResponseBody
    List<PafGame> findGamesByType(HttpServletResponse response,@PathVariable("typeName") String typeName) {        
        return service.findByField("gameType", typeName);
    }
}
