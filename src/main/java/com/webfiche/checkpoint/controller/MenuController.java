package com.webfiche.checkpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.webfiche.checkpoint.config.AppStartupService;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;

@Controller
public class MenuController extends BaseController {

    @Autowired private AppStartupService appStartupService;

    @GetMapping("/Menu.action")
    public String menu(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("nodeName", appProperties.getNodeName());
        model.addAttribute("applDate", userSession.getUser() != null ? userSession.getUser().getApplDate() : "");

        ProductSelector myProds = userSession.getMyProds();
        if (myProds != null) {
            ProductDetail[] rows = myProds.getProductrows();
            model.addAttribute("productRows", rows);
        }
        return "menu";
    }
}
