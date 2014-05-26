package com.pafgames.controller;

import com.pafgames.config.SpringMongoConfig;
import com.pafgames.domain.TestUser;
import com.pafgames.domain.entities.PafGame;
import com.pafgames.repository.PafGameMongoRepository;
import com.pafgames.repository.PafGameRepository;
import com.pafgames.service.GameService;
import com.pafgames.service.SimpleGameService;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

//    @Autowired
    PafGameRepository repo = new PafGameMongoRepository();
    GameService service = new SimpleGameService(repo);

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        for (int i = 0; i < 10; i++) {
            service.removeGame(service.findOneByField("name", "[C]Black Jack" + i));
        }
        for (int i = 0; i < 10; i++) {
            PafGame game = new PafGame();
            game.setName("[C]Black Jack" + i);
            game.setImgLink("https://www.paf.com/static/content/img/games/gamelisting/paf/classicblackjack.png?locale=en_GB");
            game.setPlayLink("https://www.paf.com/casino/paf/demogame.html?game=classicblackjack");
            game.setGameType("casino");
            game.setStars(4.5);
            service.saveGame(game);
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/test")
    public ModelAndView qunit(HttpServletResponse response) throws IOException {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/user")
    public @ResponseBody
    TestUser test2(HttpServletResponse response) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        TestUser user = new TestUser("Kavan", "password123");
        mongoOperation.save(user);

        user = mongoOperation.findOne(new Query(Criteria.where("username").is("Kavan")), TestUser.class);
        return user;
    }
}
