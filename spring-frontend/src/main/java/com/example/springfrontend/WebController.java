package com.example.springfrontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping("/menu")
    public String menu(@RequestParam(name="name", required=false, defaultValue="Menu") String name, Model model){
        model.addAttribute("name", name);
        return "mc-ronalds-menu";
    }
}
