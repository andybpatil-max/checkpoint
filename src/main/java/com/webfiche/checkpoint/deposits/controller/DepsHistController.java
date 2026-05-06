package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.DepsDetail;
import com.webfiche.checkpoint.deposits.beans.DepsSelector;
import com.webfiche.checkpoint.deposits.beans.PayerSelector;
import com.webfiche.checkpoint.deposits.service.DepsChexUtil;
import com.webfiche.checkpoint.deposits.service.DepsPayerUtil;

@Controller
public class DepsHistController extends DepositsBaseController {

    @Autowired private DepsChexUtil deUtil;
    @Autowired private DepsPayerUtil dpUtil;

    private DepsSelector buildSelector() {
        DepsSelector sel = new DepsSelector();
        sel.setDbUsed(getDbUsed());
        sel.setApplDate(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setImageURL(getImageUrl());
        sel.setImageDir(getImgBaseDir());
        sel.setDbTable("deps_chex");
        return sel;
    }

    @GetMapping("/DepsHist.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            ArrayList<String> histList = deUtil.GetDepsHistList(conn, getDbUsed(), getAppSchema());
            sel.setHistList(histList);
            PayerSelector payerSel = new PayerSelector();
            payerSel.setDbUsed(getDbUsed());
            TreeMap<String, String> payerNames = dpUtil.GetPayerNamesList(conn, payerSel);
            sel.setPayerNamesList(payerNames);
            TreeMap<String, String> payerCountries = dpUtil.GetPayerCountryList(conn, payerSel);
            sel.setPayerCountryList(payerCountries);
            deUtil.GetDepsAccountList(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depshist";
    }

    @GetMapping(value = "/DepsHist.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromPeriod,
                       @RequestParam(defaultValue = "") String toPeriod,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       @RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromCheck,
                       @RequestParam(defaultValue = "") String toCheck,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            ArrayList<String> histList = deUtil.GetDepsHistList(conn, getDbUsed(), getAppSchema());
            sel.setHistList(histList);
            sel.setCh_from_period(fromPeriod);
            sel.setCh_to_period(toPeriod);
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            sel.setCh_from_account(fromAccount);
            sel.setCh_to_account(toAccount);
            sel.setCh_from_check(fromCheck);
            sel.setCh_to_check(toCheck);
            PayerSelector payerSel = new PayerSelector();
            payerSel.setDbUsed(getDbUsed());
            TreeMap<String, String> payerNames = dpUtil.GetPayerNamesList(conn, payerSel);
            sel.setPayerNamesList(payerNames);
            deUtil.GetDepsAccountList(conn, sel);
            deUtil.GetHistRows(conn, histList, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depshist";
    }

    @GetMapping(value = "/DepsHist.action", params = "action=Detail")
    public String detail(@RequestParam int recIndex, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("error", "Detail view requires session state — use View first.");
        model.addAttribute("user", userSession.getUser());
        return "deposits/depshistdetail";
    }

    @GetMapping(value = "/DepsHist.action", params = "action=ViewImages")
    public String images(@RequestParam(defaultValue = "") String fromPeriod,
                         @RequestParam(defaultValue = "") String toPeriod,
                         @RequestParam(defaultValue = "") String fromDate,
                         @RequestParam(defaultValue = "") String toDate,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = buildSelector();
            ArrayList<String> histList = deUtil.GetDepsHistList(conn, getDbUsed(), getAppSchema());
            sel.setHistList(histList);
            sel.setCh_from_period(fromPeriod);
            sel.setCh_to_period(toPeriod);
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            deUtil.GetHistRows(conn, histList, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("depsDetail", new DepsDetail());
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depshistimage";
    }
}
