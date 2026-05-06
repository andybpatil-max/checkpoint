package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.CurrencyDetail;
import com.webfiche.checkpoint.sysadmin.beans.CurrencySelector;
import com.webfiche.checkpoint.sysadmin.service.SysadCurrencyUtil;

@Controller
public class CurrencyController extends SysadBaseController {

    @Autowired private SysadCurrencyUtil currUtil;

    @GetMapping("/Currency.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            CurrencySelector sel = new CurrencySelector();
            sel.setDbUsed(getDbUsed());
            currUtil.GetCurrencyRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/currency";
    }

    @GetMapping(value = "/Currency.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("currDetail", new CurrencyDetail());
        model.addAttribute("actionMode", "Add");
        model.addAttribute("user", userSession.getUser());
        return "sysadmin/currencynew";
    }

    @GetMapping(value = "/Currency.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String currId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            CurrencySelector sel = new CurrencySelector();
            sel.setDbUsed(getDbUsed());
            currUtil.GetCurrencyRows(conn, sel, currId);
            CurrencyDetail cd = sel.getCurrRows().length > 0 ? sel.getCurrRows()[0] : new CurrencyDetail();
            model.addAttribute("currDetail", cd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/currencynew";
    }

    @PostMapping(value = "/Currency.action", params = "action=Add")
    public String add(CurrencyDetail currDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            currUtil.InsertUpdateCurrency(conn, currDetail, getDbUsed(), userId, 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/currencynew";
        }
        return "redirect:/Currency.action";
    }

    @PostMapping(value = "/Currency.action", params = "action=Update")
    public String update(CurrencyDetail currDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            currUtil.InsertUpdateCurrency(conn, currDetail, getDbUsed(), userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/currencynew";
        }
        return "redirect:/Currency.action";
    }

    @PostMapping(value = "/Currency.action", params = "action=Delete")
    public String delete(@RequestParam String currId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            CurrencyDetail cd = new CurrencyDetail();
            cd.setCurrCode(currId);
            cd.setDbUsed(getDbUsed());
            currUtil.DeleteCurrency(conn, cd, getDbUsed(), userId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Currency.action";
    }
}
