package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.AcctentryDetail;
import com.webfiche.checkpoint.deposits.beans.AcctentrySelector;
import com.webfiche.checkpoint.deposits.beans.DepsDetail;
import com.webfiche.checkpoint.deposits.beans.DepsSelector;
import com.webfiche.checkpoint.deposits.service.DepsChexUtil;
import com.webfiche.checkpoint.deposits.service.DepsDDAUtil;
import com.webfiche.checkpoint.deposits.service.DepsPayerUtil;
import com.webfiche.checkpoint.inclear.service.InclAcctUtil;

@Controller
public class DepositsPostController extends DepositsBaseController {

    @Autowired private DepsChexUtil dcUtil;
    @Autowired private DepsDDAUtil ddaUtil;
    @Autowired private DepsPayerUtil dpUtil;
    @Autowired private InclAcctUtil acUtil;

    @GetMapping(value = "/DepositsPost.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String chexSource,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector depsSel = new DepsSelector();
            depsSel.setDbUsed(getDbUsed());
            depsSel.setApplDate(getApplDate());
            depsSel.setBankId(getBankId());
            depsSel.setDefCurr(getDefCurr());
            depsSel.setImageURL(getImageUrl());
            depsSel.setImageDir(getImgBaseDir());
            depsSel.setDbTable("deps_chex");

            AcctentrySelector acctSel = new AcctentrySelector();
            acctSel.setDbUsed(getDbUsed());
            acctSel.setDbTable("deps_chex");
            acctSel.setChexSource(chexSource.isEmpty() ? "ALL" : chexSource);
            acctSel.setActionFlag("View");

            TreeMap<String, String> payerList = dpUtil.GetPayerList(conn, acctSel);
            acctSel.setPayerList(payerList);
            acUtil.GetAccountRows(conn, acctSel);
            int rowCount = dcUtil.GetDepsRows(conn, depsSel, "P", acctSel.getChexSource());
            if (rowCount > 0) {
                acctSel = ddaUtil.ProcessPostings(conn, depsSel, acctSel);
            }
            model.addAttribute("depsSel", depsSel);
            model.addAttribute("acctSel", acctSel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depsposting";
    }

    @GetMapping("/DepositsPost.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("depsSel", new DepsSelector());
        model.addAttribute("acctSel", new AcctentrySelector());
        model.addAttribute("depsDetail", new DepsDetail());
        model.addAttribute("acctDetail", new AcctentryDetail());
        model.addAttribute("user", userSession.getUser());
        return "deposits/depsposting";
    }
}
