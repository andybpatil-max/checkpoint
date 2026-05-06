package com.webfiche.checkpoint.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {

    @GetMapping({"/login", "/Logon.action"})
    public String showLogin(Model model, HttpSession session) {
        if (userSession.isLoggedIn()) {
            return "redirect:/Menu.action";
        }
        return "login/logon";
    }

    @GetMapping("/Logoff.action")
    public String logoff(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
