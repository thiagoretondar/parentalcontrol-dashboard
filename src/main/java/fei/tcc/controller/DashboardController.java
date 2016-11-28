package fei.tcc.controller;

import fei.tcc.dto.UsersRegisteredDto;
import fei.tcc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by thiagoretondar on 15/11/16.
 */
@Controller
public class DashboardController {

    private UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        // application will not contain login action, setting here the parent id
        Integer parentId = 8;
        model.addAttribute("username", "Thiago Retondar");

        // getting users for this parent
        List<UsersRegisteredDto> users = userService.getUsers(parentId);
        model.addAttribute("usersList", users);


        // set first id
        model.addAttribute("userId", users.get(0).getId());

        return "dashboard";
    }

    @RequestMapping("/user/{userId}")
    public String homeUser(@PathVariable("userId") Integer userId, Model model) {
        // application will not contain login action, setting here the parent id
        Integer parentId = 8;
        model.addAttribute("username", "Thiago Retondar");

        // getting users for this parent
        List<UsersRegisteredDto> users = userService.getUsers(parentId);
        model.addAttribute("usersList", users);


        // set first id
        model.addAttribute("userId", userId);

        return "dashboard";
    }

}
