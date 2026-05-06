package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.beans.InclEODParams;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;
import com.webfiche.checkpoint.inclear.service.InclEodUtil;

@Controller
public class InclEodController extends InclearBaseController {

    @Autowired private InclEodUtil eodUtil;
    @Autowired private InclChexUtil chUtil;

    @GetMapping("/InclEod.action")
    public String eodMain(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            InclEODParams eodParams = new InclEODParams();
            eodParams.setDbUsed(getDbUsed());
            eodParams.setUserId(getUserId());
            eodUtil.EODSetEodParams(conn, eodParams);
            ChexSelector sel = new ChexSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setDefCurr(getDefCurr());
            sel.setEod_status(eodParams.getEod_flag());
            TreeMap<String, String> chexStatus = chUtil.GetChexStatus(conn, "chexstatus");
            sel.setChexStatus(chexStatus);
            int rowCount = chUtil.GetChexStatusSummaryRows(conn, sel);
            if (rowCount > 0) sel.setDetail_count(rowCount);
            model.addAttribute("sel", sel);
            model.addAttribute("eodParams", eodParams);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/incleod";
    }

    @PostMapping(value = "/InclEod.action", params = "action=Confirm")
    public String confirm(InclEODParams eodParams, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            eodParams.setDbUsed(getDbUsed());
            eodParams.setUserId(getUserId());
            eodUtil.EODSetEodParams(conn, eodParams);
            ChexSelector sel = new ChexSelector();
            sel.setDbUsed(getDbUsed());
            if ("N".equals(eodParams.getEod_flag())) {
                int detailCount = chUtil.GetChexStatusSummaryRows(conn, sel);
                int eodResult = eodUtil.EODPerform_EOD(conn, eodParams);
                if (detailCount == 0 || eodResult == 1) {
                    eodUtil.EODUpdateControl(conn, eodParams);
                    eodUtil.EODCloseTheDay(conn, eodParams);
                }
            }
            model.addAttribute("eodParams", eodParams);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/incleoddone";
    }

    @PostMapping(value = "/InclEod.action", params = "action=UnDo")
    public String undo(InclEODParams eodParams, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            eodParams.setDbUsed(getDbUsed());
            eodUtil.EODSetEodParams(conn, eodParams);
            if ("Y".equals(eodParams.getEod_flag())) {
                eodUtil.EODRestoreTables(conn, eodParams);
            }
            model.addAttribute("eodParams", eodParams);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/incleoddone";
    }
}
