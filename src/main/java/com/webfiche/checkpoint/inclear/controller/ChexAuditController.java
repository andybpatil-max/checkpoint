package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class ChexAuditController extends InclearBaseController {

    @Autowired private InclChexUtil chUtil;

    private ChexSelector buildAuditSelector(Connection conn, String fromAccount,
            String toAccount, String fromDate, String toDate) {
        ChexSelector sel = new ChexSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setCh_from_account(fromAccount);
        sel.setCh_to_account(toAccount);
        sel.setCh_from_date(fromDate);
        sel.setCh_to_date(toDate);
        return sel;
    }

    @GetMapping("/ChexAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        ChexSelector sel = null;
        try (Connection conn = openConnection()) {
            sel = buildAuditSelector(conn, "", "", "", "");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("sel", sel != null ? sel : new ChexSelector());
        model.addAttribute("user", userSession.getUser());
        return "inclear/chexaudit";
    }

    @GetMapping(value = "/ChexAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        ChexSelector sel = null;
        try (Connection conn = openConnection()) {
            sel = buildAuditSelector(conn, fromAccount, toAccount, fromDate, toDate);
            chUtil.GetChexLogRows(conn, sel);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("sel", sel != null ? sel : new ChexSelector());
        model.addAttribute("user", userSession.getUser());
        return "inclear/chexaudit";
    }
}
