package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.PayerSelector;
import com.webfiche.checkpoint.deposits.service.DepsPayerUtil;

@Controller
public class DepositsPayerAuditController extends DepositsBaseController {

    @Autowired private DepsPayerUtil dpUtil;

    @GetMapping("/DepositsPayerAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = new PayerSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setDefCurr(getDefCurr());
            sel.setDbTable("payerslog");
            TreeMap<String, String> payersList = dpUtil.GetPayerNamesList(conn, sel);
            sel.setPayersList(payersList);
            ArrayList<String> abaList = dpUtil.GetPayerAbaList(conn, sel);
            sel.setPayerAbaList(abaList);
            ArrayList<String> acctList = dpUtil.GetPayerAcctList(conn, sel);
            sel.setPayerAcctList(acctList);
            ArrayList<String> dateList = dpUtil.GetPayerLogDateList(conn);
            sel.setPayerDateList(dateList);
            ArrayList<String> userList = dpUtil.GetPayerLogUserList(conn);
            sel.setPayerUserList(userList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payersaudit";
    }

    @GetMapping(value = "/DepositsPayerAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String payerAbaFrom,
                       @RequestParam(defaultValue = "") String payerAbaTo,
                       @RequestParam(defaultValue = "") String payerAcctFrom,
                       @RequestParam(defaultValue = "") String payerAcctTo,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = new PayerSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setDefCurr(getDefCurr());
            sel.setDbTable("payerslog");
            sel.setPayerAbaFrom(payerAbaFrom);
            sel.setPayerAbaTo(payerAbaTo);
            sel.setPayerAcctFrom(payerAcctFrom);
            sel.setPayerAcctTo(payerAcctTo);
            TreeMap<String, String> payersList = dpUtil.GetPayerNamesList(conn, sel);
            sel.setPayersList(payersList);
            ArrayList<String> abaList = dpUtil.GetPayerAbaList(conn, sel);
            sel.setPayerAbaList(abaList);
            ArrayList<String> acctList = dpUtil.GetPayerAcctList(conn, sel);
            sel.setPayerAcctList(acctList);
            ArrayList<String> dateList = dpUtil.GetPayerLogDateList(conn);
            sel.setPayerDateList(dateList);
            ArrayList<String> userList = dpUtil.GetPayerLogUserList(conn);
            sel.setPayerUserList(userList);
            dpUtil.GetPayerLogRows(conn, sel, "");
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payersaudit";
    }
}
