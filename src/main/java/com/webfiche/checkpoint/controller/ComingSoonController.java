package com.webfiche.checkpoint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingSoonController extends BaseController {

    @GetMapping("/ComingSoon.action")
    public String comingSoon(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("user", userSession.getUser());
        return "coming-soon";
    }
}
