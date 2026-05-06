package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.StoppayDetail;
import com.webfiche.checkpoint.inclear.beans.StoppaySelector;
import com.webfiche.checkpoint.inclear.service.InclStopUtil;

@Controller
public class StoppayController extends InclearBaseController {

    @Autowired private InclStopUtil stopUtil;

    private StoppaySelector buildSelector() {
        StoppaySelector sel = new StoppaySelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        return sel;
    }

    @GetMapping("/Stoppay.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppaySelector sel = buildSelector();
            stopUtil.GetStoppayRows(conn, sel);
            ArrayList<String> acctList = stopUtil.GetStopAccountList(conn);
            sel.setAcctList(acctList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/stoppay";
    }

    @GetMapping(value = "/Stoppay.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromCheck,
                       @RequestParam(defaultValue = "") String toCheck,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppaySelector sel = buildSelector();
            sel.setSp_from_acct(fromAccount);
            sel.setSp_to_acct(toAccount);
            sel.setSp_from_check(fromCheck);
            sel.setSp_to_check(toCheck);
            stopUtil.GetStoppayRows(conn, sel);
            ArrayList<String> acctList = stopUtil.GetStopAccountList(conn);
            sel.setAcctList(acctList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/stoppayinq";
    }

    @GetMapping(value = "/Stoppay.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        StoppayDetail sd = new StoppayDetail();
        sd.setDbUsed(getDbUsed());
        sd.setAppl_date(getApplDate());
        sd.setBankId(getBankId());
        sd.setDefCurr(getDefCurr());
        model.addAttribute("stopDetail", sd);
        model.addAttribute("actionMode", "New");
        model.addAttribute("user", userSession.getUser());
        return "inclear/stoppayAMD";
    }

    @GetMapping(value = "/Stoppay.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String acctNum,
                             @RequestParam(defaultValue = "") String checkNum,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppaySelector sel = buildSelector();
            stopUtil.GetStoppayRows(conn, sel, acctNum, checkNum, "");
            StoppayDetail sd = sel.getArow() != null ? sel.getArow() : new StoppayDetail();
            sd.setDbUsed(getDbUsed());
            model.addAttribute("stopDetail", sd);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/stoppayAMD";
    }

    @PostMapping(value = "/Stoppay.action", params = "action=Add")
    public String add(StoppayDetail stopDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        stopDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            stopUtil.InsertUpdateStop(conn, stopDetail, getUserId(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("stopDetail", stopDetail);
            model.addAttribute("actionMode", "New");
            model.addAttribute("user", userSession.getUser());
            return "inclear/stoppayAMD";
        }
        return "redirect:/Stoppay.action";
    }

    @PostMapping(value = "/Stoppay.action", params = "action=Update")
    public String update(StoppayDetail stopDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        stopDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            stopUtil.InsertUpdateStop(conn, stopDetail, getUserId(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("stopDetail", stopDetail);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
            return "inclear/stoppayAMD";
        }
        return "redirect:/Stoppay.action";
    }

    @PostMapping(value = "/Stoppay.action", params = "action=Delete")
    public String delete(@RequestParam(defaultValue = "") String acctNum,
                         @RequestParam(defaultValue = "") String checkNum,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            StoppayDetail sd = new StoppayDetail();
            sd.setDbUsed(getDbUsed());
            sd.setStopay_account_num(acctNum);
            sd.setStopay_check_num(checkNum);
            stopUtil.DeleteStoppay(conn, sd, getUserId());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Stoppay.action";
    }
}
