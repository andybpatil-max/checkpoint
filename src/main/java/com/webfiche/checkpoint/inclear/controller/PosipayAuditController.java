package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.PosipaySelector;
import com.webfiche.checkpoint.inclear.service.InclPosiUtil;

@Controller
public class PosipayAuditController extends InclearBaseController {

    @Autowired private InclPosiUtil posiUtil;

    @GetMapping("/PosipayAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipaySelector sel = new PosipaySelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            ArrayList<String> acctList = posiUtil.GetPosiLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = posiUtil.GetPosiLogDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = posiUtil.GetPosiLogUserList(conn);
            sel.setUserList(userList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/posipayaudit";
    }

    @GetMapping(value = "/PosipayAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipaySelector sel = new PosipaySelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setPp_from_acct(fromAccount);
            sel.setPp_to_acct(toAccount);
            sel.setPp_log_date(fromDate);
            ArrayList<String> acctList = posiUtil.GetPosiLogAccountList(conn);
            sel.setAcctList(acctList);
            ArrayList<String> dateList = posiUtil.GetPosiLogDateList(conn);
            sel.setDateList(dateList);
            ArrayList<String> userList = posiUtil.GetPosiLogUserList(conn);
            sel.setUserList(userList);
            posiUtil.GetPosipayLogRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/posipayaudit";
    }
}
