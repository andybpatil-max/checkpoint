package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.webfiche.checkpoint.deposits.service.UploadDepMicr;
import com.webfiche.checkpoint.deposits.service.ViewDepStats;
import com.webfiche.checkpoint.sysadmin.beans.ControlDetail;
import com.webfiche.checkpoint.sysadmin.beans.ControlSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadControlUtil;

@Controller
public class LoadDepositsController extends DepositsBaseController {

    @Autowired private SysadControlUtil ctlUtil;
    @Autowired private UploadDepMicr upLoadMicr;

    @GetMapping("/LoadDeposits.action")
    public String loadData(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ControlSelector ctlSel = new ControlSelector();
            ctlUtil.GetControlRow(conn, ctlSel, "A");
            ControlDetail ctlDetail = ctlSel.getARow();
            ControlSelector ctlSelD = new ControlSelector();
            ctlUtil.GetControlRow(conn, ctlSelD, "D");
            ControlDetail ctlDetailD = ctlSelD.getARow();
            model.addAttribute("ctlDetail", ctlDetail);
            model.addAttribute("ctlDetailD", ctlDetailD);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depsupload";
    }

    @PostMapping(value = "/LoadDeposits.action", params = "action=LoadFile")
    public String loadFile(ControlDetail ctlDetail, ControlDetail ctlDetailD, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ctlDetail.setUser_name(getUserId());
            int result = upLoadMicr.LoadDepositMicr(conn, ctlDetail, ctlDetailD);
            if (result != 1) {
                model.addAttribute("error", "Load failed");
                model.addAttribute("ctlDetail", ctlDetail);
                model.addAttribute("ctlDetailD", ctlDetailD);
                model.addAttribute("user", userSession.getUser());
                return "deposits/depsupload";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ctlDetail", ctlDetail);
            model.addAttribute("ctlDetailD", ctlDetailD);
            model.addAttribute("user", userSession.getUser());
            return "deposits/depsupload";
        }
        return "redirect:/LoadDeposits.action?action=ViewStats";
    }

    @GetMapping(value = "/LoadDeposits.action", params = "action=ViewStats")
    public String viewStats(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ViewDepStats vdb = new ViewDepStats(conn);
            ArrayList<ArrayList<String>> stats = vdb.getLoadStats();
            model.addAttribute("stats", stats);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/viewStats";
    }
}
