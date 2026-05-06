package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.DepsSelector;
import com.webfiche.checkpoint.deposits.beans.DepsDetail;
import com.webfiche.checkpoint.deposits.service.DepsChexUtil;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class DepositsController extends DepositsBaseController {

    @Autowired private DepsChexUtil deUtil;
    @Autowired private InclChexUtil chUtil;

    private DepsSelector buildSelector() {
        DepsSelector sel = new DepsSelector();
        sel.setDbUsed(getDbUsed());
        sel.setApplDate(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setImageURL(getImageUrl());
        sel.setImageDir(getImgBaseDir());
        return sel;
    }

    @GetMapping("/Deposits.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            deUtil.GetDepsRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/deposits";
    }

    @GetMapping(value = "/Deposits.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            deUtil.GetDepsRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depositsinq";
    }

    @GetMapping(value = "/Deposits.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String acctNum,
                             @RequestParam(defaultValue = "") String checkNum,
                             @RequestParam(defaultValue = "") String uniqueIsn,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            deUtil.GetDepsRows(conn, sel, acctNum, checkNum, uniqueIsn);
            DepsDetail dd = sel.getCheckrows().length > 0 ? sel.getCheckrows()[0] : new DepsDetail();
            model.addAttribute("depsDetail", dd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depsmodify";
    }

    @PostMapping(value = "/Deposits.action", params = "action=Update")
    public String update(DepsDetail depsDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            depsDetail.setModuser(getUserId());
            deUtil.InsertUpdateDeps(conn, "deps_chex", "deps_chexlog", depsDetail, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "deposits/depsmodify";
        }
        return "redirect:/Deposits.action";
    }

    @GetMapping(value = "/Deposits.action", params = "action=SummaryView")
    public String summary(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            deUtil.GetDepsStatusSummaryRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depssummary";
    }
}
