package com.webfiche.checkpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.config.AppStartupService;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;

@Controller
public class MenuController extends BaseController {

    @Autowired private AppStartupService appStartupService;

    @GetMapping("/")
    public String root() {
        return "redirect:/Menu.action";
    }

    @GetMapping("/Menu.action")
    public String menu(Model model) {
        if (!userSession.isLoggedIn()) return "redirect:/login";
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("nodeName", appProperties.getNodeName());
        model.addAttribute("applDate", userSession.getUser() != null ? userSession.getUser().getApplDate() : "");
        ProductSelector myProds = userSession.getMyProds();
        if (myProds != null) {
            model.addAttribute("productRows", myProds.getProductrows());
        }
        return "menu";
    }

    @GetMapping(value = "/Menu.action", params = {"prod", "menu", "func"})
    public String dispatch(@RequestParam String prod,
                           @RequestParam String menu,
                           @RequestParam String func) {
        if (!userSession.isLoggedIn()) return "redirect:/login";
        ProductSelector myProds = userSession.getMyProds();
        if (myProds == null) return "redirect:/Menu.action";

        for (ProductDetail pd : myProds.getProductrows()) {
            String pdProd = pd.getProduct_id().trim();
            String pdMenu = pd.getProduct_menu_id().trim();
            String pdFunc = pd.getProduct_menu_func_id().trim();
            if (pdProd.equals(prod.trim()) && pdMenu.equals(menu.trim()) && pdFunc.equals(func.trim())) {
                String url = resolveSpringUrl(pd.getProduct_pmf_link(), pd.getProduct_pmf_param());
                if (url != null) return "redirect:" + url;
                break;
            }
        }
        return "redirect:/Menu.action";
    }

    private String resolveSpringUrl(String stripesLink, String param) {
        if (stripesLink == null || stripesLink.isBlank()) return null;
        String link = stripesLink.trim();
        if (link.equals("NA") || link.startsWith("NA?")) return null;
        if (link.equals("Menu.action") || link.startsWith("Menu.action?")) return "/Menu.action";

        // econtroller.xxx.actions.FooBarAction → /FooBar.action
        int lastDot = link.lastIndexOf('.');
        String className = (lastDot >= 0) ? link.substring(lastDot + 1) : link;
        if (className.endsWith("Action")) {
            className = className.substring(0, className.length() - 6);
        }
        String url = "/" + className + ".action";
        if (param != null && !param.isBlank()) {
            url += "?" + param.trim();
        }
        return url;
    }
}
