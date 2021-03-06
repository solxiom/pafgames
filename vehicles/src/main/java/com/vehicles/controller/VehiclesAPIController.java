package com.vehicles.controller;

import com.vehicles.domain.CommandRequest;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.entities.Order;
import com.vehicles.domain.enums.LastUpdate;
import com.vehicles.exceptions.BadCommandException;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.ColorMongoRepository;
import com.vehicles.repository.LastUpdateMongoRepository;
import com.vehicles.repository.OrderMongoRepository;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.repository.interfaces.LastUpdateRepository;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.SimpleColorService;
import com.vehicles.service.SimpleLastUpdateService;
import com.vehicles.service.SimpleOrderService;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.LastUpdateService;
import com.vehicles.service.interfaces.OrderService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kavan
 */
@Controller
@RequestMapping("/api")
public class VehiclesAPIController {

    private final String updateDBKey = "paf_interview_proto_1234567";
    private final Logger logger = Logger.getLogger(getClass().getName());
    CommandUtil util = new CommandUtil();

    LastUpdateRepository updateRepo = new LastUpdateMongoRepository();
    OrderRepository orderRepo = new OrderMongoRepository();
    ColorRepository colorRepo = new ColorMongoRepository();

    LastUpdateService updateService = new SimpleLastUpdateService(updateRepo);
    ColorService colorService = new SimpleColorService(colorRepo,updateService,updateDBKey);
    OrderService orderService = new SimpleOrderService(orderRepo, colorService, updateService,updateDBKey);

    @RequestMapping(value = "/order/new", method = RequestMethod.POST)
    public void addOrder(HttpServletResponse response, @RequestBody CommandRequest command) {
        Order order = null;
        try {
            order = util.convertCommandToOrder(command.getCommand());
        } catch (BadCommandException ex) {
            response.setStatus(400);//Bad request
            logger.info("new Order: bad command exception occured!");
            return;
        }
        try {
            orderService.save(order);
        } catch (StoarageOutOfColorException ex) {
            response.setStatus(403);//"Forbidden"
            logger.info("new Order: StoarageOutOfColor Exception occured!");
            return;

        } catch (Exception e) {
            response.setStatus(500);//Internal Server Error
            logger.info("new Order: Internal server error with message:" + e.getMessage());
            return;
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/lastupdate", method = RequestMethod.GET)
    public @ResponseBody
    LastUpdate getLastDBUpdateTime() {
        logger.info("returning last update time");
        return updateService.findOneByField("id", updateDBKey);
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> listOrders() {
        logger.info("returning orders list...");
        return orderService.findAll();
    }

    @RequestMapping(value = "/color/list", method = RequestMethod.GET)
    public @ResponseBody
    List<String> listColors(HttpServletResponse response) {
        logger.info("returning colors list...");
        List<String> colors = new LinkedList<>();
        for (Color cl : colorService.findAll()) {
            colors.add(cl.getName().toString());
        }
        response.setStatus(200);
        return colors;
    }

    @RequestMapping(value = "/color/refill", method = RequestMethod.POST)
    public void refillColors(HttpServletResponse response) {
        logger.info("refilling colors...");
        colorService.refillMissedColors();
        response.setStatus(200);
    }

}
