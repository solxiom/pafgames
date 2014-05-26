/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.repository;

import com.pafgames.domain.entities.PafGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan soleimanbeigi
 */
public class PafGameMongoRepositoryTest {

    PafGameMongoRepository repo;

    public PafGameMongoRepositoryTest() {
    }

    @Before
    public void setUp() {
        repo = new PafGameMongoRepository();

    }

    @After
    public void tearDown() {
    }
    /**
     * 
     */

    @Test
    public void testSave() {
        PafGame game = new PafGame();
        game.setName("TestCase: Black Jack");
        game.setImgLink("https://www.paf.com/static/content/img/games/gamelisting/paf/classicblackjack.png?locale=en_GB");
        game.setPlayLink("https://www.paf.com/casino/paf/demogame.html?game=classicblackjack");
        game.setGameType("casino");
        game.setStars(4.5);
        repo.remove(repo.findOneByField("name", "TestCase: Black Jack"));
        PafGame beforeSave = repo.findOneByField("name", "TestCase: Black Jack");
        assertNull("PafGame Object should be null before saving", beforeSave);
        repo.save(game);
        PafGame afterSave = repo.findOneByField("name", "TestCase: Black Jack");
        assertNotNull("PafGame shouldn't be null after save operation", afterSave);
        repo.remove(game);
    }

    @Test
    public void testRemove() {
        PafGame game = new PafGame();
        game.setName("TestCase: Black Jack");
        game.setImgLink("https://www.paf.com/static/content/img/games/gamelisting/paf/classicblackjack.png?locale=en_GB");
        game.setPlayLink("https://www.paf.com/casino/paf/demogame.html?game=classicblackjack");
        game.setGameType("casino");
        game.setStars(4.5);
        repo.remove(repo.findOneByField("name", "TestCase: Black Jack"));
        PafGame beforeSave = repo.findOneByField("name", "TestCase: Black Jack");
        assertNull("PafGame Object should be null before saving", beforeSave);
        repo.save(game);
        PafGame afterSave = repo.findOneByField("name", "TestCase: Black Jack");
        assertNotNull("PafGame shouldn't be null after save operation", afterSave);
        repo.remove(game);
        assertNull("PafGame Object should be null after remove!", beforeSave);
    }
    /**
     * adding 10 PafGame object to the repo then check the size of collection
     * removing all added objects and then checking the size again
     */
    @Test
    public void testFindAll() {
        int size_beforSave = repo.findAll().size();
        for (int i = 0; i < 10; i++) {
            PafGame game = new PafGame();
            game.setName("TestCase: Black Jack"+i);
            repo.save(game);
        }
        int size_afterSave = repo.findAll().size();
        assertTrue("the collection size is not correct after multiple size operations",size_beforSave + 10 == size_afterSave);
        for(int i = 0; i< 10; i++){
            repo.remove(repo.findOneByField("name", "TestCase: Black Jack"+i));
        }
        int size_afterRemoveAll = repo.findAll().size();
        assertTrue("the collection size is not correct after multiple size operations",size_beforSave == size_afterRemoveAll);
    }

}
