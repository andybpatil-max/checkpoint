package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.DepsSelector;
import com.webfiche.checkpoint.deposits.service.DepsChexUtil;

@Controller
public class DepositsAuditController extends DepositsBaseController {

    @Autowired private DepsChexUtil dcUtil;

    @GetMapping("/DepositsAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = new DepsSelector();
            sel.setDbUsed(getDbUsed());
            sel.setApplDate(getApplDate());
            sel.setBankId(getBankId());
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depsaudit";
    }

    @GetMapping(value = "/DepositsAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = new DepsSelector();
            sel.setDbUsed(getDbUsed());
            sel.setApplDate(getApplDate());
            sel.setBankId(getBankId());
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            dcUtil.GetDepsLogRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depsaudit";
    }
}
