package com.vehicles.controller;

import com.vehicles.domain.entities.Color;
import com.vehicles.domain.entities.Order;
import com.vehicles.exceptions.BadCommandException;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.ColorMongoRepository;
import com.vehicles.repository.OrderMongoRepository;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.SimpleColorService;
import com.vehicles.service.SimpleOrderService;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.OrderService;
import java.util.LinkedList;
import java.util.List;
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

    CommandUtil util = new CommandUtil();
    OrderRepository orderRepo = new OrderMongoRepository();
    ColorRepository colorRepo = new ColorMongoRepository();
    ColorService colorService = new SimpleColorService(colorRepo);
    OrderService orderService = new SimpleOrderService(orderRepo, colorService);

    @RequestMapping(value = "/order/new", method = RequestMethod.POST)
    public void addOrder(HttpServletResponse response, @RequestBody String command) {
        Order order = null;
        try {
            order = util.convertCommandToOrder(command);
        } catch (BadCommandException ex) {
            response.setStatus(400);//Bad request
        }
        try {
            orderService.save(order);
        } catch (StoarageOutOfColorException ex) {
            response.setStatus(403);//"Forbidden"
        }catch(Exception e){
            response.setStatus(500);//Internal Server Error
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> listOrders() {
        return orderService.findAll();
    }

    @RequestMapping(value = "/color/list", method = RequestMethod.GET)
    public @ResponseBody
    List<String> listColors(HttpServletResponse response) {
        List<String> colors = new LinkedList<>();
        for (Color cl : colorService.findAll()) {
            colors.add(cl.getName().toString());
        }
        response.setStatus(200);
        return colors;
    }

    @RequestMapping(value = "/color/refill", method = RequestMethod.GET)
    public void refillColors(HttpServletResponse response) {
        colorService.refillMissedColors();
        response.setStatus(200);
    }

}
