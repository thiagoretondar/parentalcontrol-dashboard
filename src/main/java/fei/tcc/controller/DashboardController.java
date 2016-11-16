package fei.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thiagoretondar on 15/11/16.
 */
@Controller
public class DashboardController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "Thiago");
        return "dashboard";
    }

}
