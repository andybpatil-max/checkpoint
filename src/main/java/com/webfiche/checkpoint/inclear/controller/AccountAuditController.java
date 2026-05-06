package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.AccountSelector;
import com.webfiche.checkpoint.inclear.service.InclAcctUtil;

@Controller
public class AccountAuditController extends InclearBaseController {

    @Autowired private InclAcctUtil acUtil;

    @GetMapping("/AccountAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountSelector sel = new AccountSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            ArrayList<String> acctList = acUtil.GetLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = acUtil.GetLogAccountDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = acUtil.GetLogAccountUserList(conn);
            sel.setUserList(userList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/acctaudit";
    }

    @GetMapping(value = "/AccountAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountSelector sel = new AccountSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setAccount_num_from(fromAccount);
            sel.setAccount_num_to(toAccount);
            ArrayList<String> acctList = acUtil.GetLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = acUtil.GetLogAccountDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = acUtil.GetLogAccountUserList(conn);
            sel.setUserList(userList);
            acUtil.GetLogAccountRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/acctaudit";
    }
}
