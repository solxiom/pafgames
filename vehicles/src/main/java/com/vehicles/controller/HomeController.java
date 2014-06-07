package com.vehicles.controller;

import com.vehicles.repository.PafGameMongoRepository;
import com.vehicles.repository.PafGameRepository;
import com.vehicles.service.GameService;
import com.vehicles.service.SimpleGameService;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

//    @Autowired
    PafGameRepository repo = new PafGameMongoRepository();
    GameService service = new SimpleGameService(repo);

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/test")
    public ModelAndView qunit(HttpServletResponse response) throws IOException {
        return new ModelAndView("test");
    }

//    @RequestMapping(value = "/user")
//    public @ResponseBody
//    TestUser test2(HttpServletResponse response) throws IOException {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//        TestUser user = new TestUser("Kavan", "password123");
//        mongoOperation.save(user);
//
//        user = mongoOperation.findOne(new Query(Criteria.where("username").is("Kavan")), TestUser.class);
//        return user;
//    }
    public void initTestGames(){
        
    }
}
