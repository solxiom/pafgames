/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.controller;

import com.pafgames.domain.entities.Feedback;
import com.pafgames.repository.FeedbackMongoRepository;
import com.pafgames.repository.FeedbackRepository;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author kavan
 */
@Component
public class App3 {

//    @Autowired
//    FeedbackRepository repo;
    FeedbackRepository repo = new FeedbackMongoRepository();

    public static void main(String[] args) {

        //= new FeedbackMongoRepository();
//        for (int i = 1; i < 11; i++) {
//            Feedback f = new Feedback();
//            f.setStars((i * 30) / 4.5);
//            f.setText("agda gdjahgs  asjdgajsh ");
//            repo.save(f);
//
//        }

        List<Feedback> feeds = new App3().listFeedbacks();
        for (Feedback f : feeds) {
            System.out.println(f);
        }
    }

    public List<Feedback> listFeedbacks() {
        List<Feedback> feeds = repo.findAll();
        return feeds;
    }
}
