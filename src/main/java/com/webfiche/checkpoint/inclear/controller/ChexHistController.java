package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.ChexDetail;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class ChexHistController extends InclearBaseController {

    @Autowired private InclChexUtil chUtil;

    private ChexSelector buildSelector(Connection conn) throws Exception {
        ChexSelector sel = new ChexSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setImageURL(getImageUrl());
        sel.setImageDir(getImgBaseDir() + "incl/");
        sel.setDbTable("incl_chex");
        ArrayList<String> histList = chUtil.GetChexHistList(conn, getDbUsed(), "incl_chex_history");
        sel.setHistList(histList);
        ArrayList<String> acctList = chUtil.GetChexAccountList(conn, "incl_chex");
        sel.setAcctList(acctList);
        TreeMap<String, String> rejReasons = chUtil.GetRejectReasons(conn, "rejectreasons");
        sel.setRejReasons(rejReasons);
        TreeMap<String, String> retReasons = chUtil.GetReturnReasons(conn, "returnreasons");
        sel.setRetReasons(retReasons);
        TreeMap<String, String> chexStatus = chUtil.GetChexStatus(conn, "chexstatus");
        sel.setChexStatus(chexStatus);
        return sel;
    }

    @GetMapping("/ChexHist.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector(conn);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "chexhist";
    }

    @GetMapping(value = "/ChexHist.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromPeriod,
                       @RequestParam(defaultValue = "") String toPeriod,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       @RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromCheck,
                       @RequestParam(defaultValue = "") String toCheck,
                       @RequestParam(defaultValue = "") String fromAmount,
                       @RequestParam(defaultValue = "") String toAmount,
                       @RequestParam(defaultValue = "") String currency,
                       @RequestParam(defaultValue = "N") String retstat,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector(conn);
            sel.setCh_from_period(fromPeriod);
            sel.setCh_to_period(toPeriod);
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            sel.setCh_from_account(fromAccount);
            sel.setCh_to_account(toAccount);
            sel.setCh_from_check(fromCheck);
            sel.setCh_to_check(toCheck);
            sel.setCh_from_amount(fromAmount);
            sel.setCh_to_amount(toAmount);
            sel.setCh_currency(currency);
            sel.setCh_retstat(retstat);
            chUtil.GetHistRows(conn, sel.getHistList(), sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "chexhist";
    }

    @GetMapping(value = "/ChexHist.action", params = "action=Details")
    public String details(@RequestParam(defaultValue = "") String procDate,
                          @RequestParam(defaultValue = "") String acctNum,
                          @RequestParam(defaultValue = "") String checkNum,
                          @RequestParam(defaultValue = "") String uniqueIsn,
                          Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector(conn);
            chUtil.GetHistRows(conn, procDate, acctNum, checkNum, uniqueIsn, sel);
            ChexDetail cd = sel.getCheckrows().length > 0 ? sel.getArow() : new ChexDetail();
            model.addAttribute("chexDetail", cd);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "chexhistdetail";
    }

    @GetMapping(value = "/ChexHist.action", params = "action=ViewImages")
    public String images(@RequestParam(defaultValue = "") String fromPeriod,
                         @RequestParam(defaultValue = "") String toPeriod,
                         @RequestParam(defaultValue = "") String fromDate,
                         @RequestParam(defaultValue = "") String toDate,
                         @RequestParam(defaultValue = "") String fromAccount,
                         @RequestParam(defaultValue = "") String toAccount,
                         @RequestParam(defaultValue = "") String fromCheck,
                         @RequestParam(defaultValue = "") String toCheck,
                         @RequestParam(defaultValue = "") String fromAmount,
                         @RequestParam(defaultValue = "") String toAmount,
                         @RequestParam(defaultValue = "") String currency,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector(conn);
            sel.setCh_from_period(fromPeriod);
            sel.setCh_to_period(toPeriod);
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            sel.setCh_from_account(fromAccount);
            sel.setCh_to_account(toAccount);
            sel.setCh_from_check(fromCheck);
            sel.setCh_to_check(toCheck);
            sel.setCh_from_amount(fromAmount);
            sel.setCh_to_amount(toAmount);
            sel.setCh_currency(currency);
            chUtil.GetHistRows(conn, sel.getHistList(), sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "chexhistimage";
    }
}
