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
        model.addAttribute("nodeName", appProperties.getNodeName());
        ProductSelector myProds = userSession.getMyProds();
        if (myProds != null) {
            model.addAttribute("productRows", myProds.getProductrows());
        }
        return "menu";
    }

    @GetMapping(value = "/Menu.action", params = "home")
    public String home() {
        if (!userSession.isLoggedIn()) return "redirect:/login";
        if (userSession.getUser() != null) {
            userSession.getUser().setCurrentProd("");
            userSession.getUser().setCurrentAppl("Checkpoint");
            userSession.getUser().setUserMenu("");
        }
        return "redirect:/Menu.action";
    }

    @GetMapping(value = "/Menu.action", params = {"prod"})
    public String selectProduct(@RequestParam String prod) {
        if (!userSession.isLoggedIn()) return "redirect:/login";
        ProductSelector myProds = userSession.getMyProds();
        if (myProds != null && userSession.getUser() != null) {
            for (ProductDetail pd : myProds.getProductrows()) {
                if (pd.getProduct_id().trim().equals(prod.trim())
                        && pd.getProduct_menu_id().trim().isEmpty()
                        && pd.getProduct_menu_func_id().trim().isEmpty()) {
                    userSession.getUser().setCurrentProd(prod.trim());
                    userSession.getUser().setCurrentAppl(pd.getProduct_pmf_desc());
                    userSession.getUser().setUserMenu("");
                    break;
                }
            }
        }
        return "redirect:/Menu.action";
    }

    @GetMapping(value = "/Menu.action", params = {"prod", "menu"})
    public String selectMenu(@RequestParam String prod, @RequestParam String menu) {
        if (!userSession.isLoggedIn()) return "redirect:/login";
        if (userSession.getUser() != null) {
            userSession.getUser().setCurrentProd(prod.trim());
            userSession.getUser().setUserMenu(menu.trim());
        }
        return "redirect:/Menu.action";
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
                // Track current product in session so footer can filter by it
                if (userSession.getUser() != null) {
                    userSession.getUser().setCurrentProd(pdProd);
                    // Find the module-level description for this product
                    for (ProductDetail mod : myProds.getProductrows()) {
                        if (mod.getProduct_id().trim().equals(pdProd)
                                && mod.getProduct_menu_id().trim().isEmpty()
                                && mod.getProduct_menu_func_id().trim().isEmpty()) {
                            userSession.getUser().setCurrentAppl(mod.getProduct_pmf_desc());
                            break;
                        }
                    }
                }
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
