package be.vdab.restclient.controllers;

import be.vdab.restclient.restclients.ReqResClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
class UserController {
    private final ReqResClient client;

    public UserController(ReqResClient client) {
        this.client = client;
    }

    @GetMapping("{id}")
    public ModelAndView getUser(@PathVariable long id) {
        var modelAndView = new ModelAndView("user");
        client.findById(id).ifPresent(user -> modelAndView.addObject(user));
        return modelAndView;
    }
}