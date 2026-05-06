package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.StoppaySelector;
import com.webfiche.checkpoint.inclear.service.InclStopUtil;

@Controller
public class StoppayAuditController extends InclearBaseController {

    @Autowired private InclStopUtil stopUtil;

    @GetMapping("/StoppayAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppaySelector sel = new StoppaySelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            ArrayList<String> acctList = stopUtil.GetStopLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = stopUtil.GetStopLogDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = stopUtil.GetStopLogUserList(conn);
            sel.setUserList(userList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/stopaudit";
    }

    @GetMapping(value = "/StoppayAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppaySelector sel = new StoppaySelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setSp_from_acct(fromAccount);
            sel.setSp_to_acct(toAccount);
            sel.setSp_log_date(fromDate);
            ArrayList<String> acctList = stopUtil.GetStopLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = stopUtil.GetStopLogDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = stopUtil.GetStopLogUserList(conn);
            sel.setUserList(userList);
            stopUtil.GetStoppayLogRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/stopaudit";
    }
}
