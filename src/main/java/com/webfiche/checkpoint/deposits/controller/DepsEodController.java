package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.webfiche.checkpoint.deposits.beans.DepsEodParams;
import com.webfiche.checkpoint.deposits.beans.DepsSelector;
import com.webfiche.checkpoint.deposits.service.DepsChexUtil;
import com.webfiche.checkpoint.deposits.service.DepsEodUtil;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class DepsEodController extends DepositsBaseController {

    @Autowired private DepsChexUtil deUtil;
    @Autowired private DepsEodUtil eodUtil;
    @Autowired private InclChexUtil chUtil;

    @GetMapping("/DepsEod.action")
    public String eodMain(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DepsSelector sel = new DepsSelector();
            DepsEodParams eodParams = new DepsEodParams();
            eodParams.setDbUsed(getDbUsed());
            eodParams.setEod_oper(getUserId());
            eodUtil.EODSetEodParams(conn, eodParams);
            String eodFlag = eodParams.getEod_flag();
            sel.setEod_status(eodFlag);
            TreeMap<String, String> chexStatus = chUtil.GetChexStatus(conn, "chexstatus");
            sel.setChexStatus(chexStatus);
            int rowCount = deUtil.GetDepsEodStatusSummary(conn, sel);
            if (rowCount > 0) sel.setDetail_count(rowCount);
            sel.setApplDate(eodParams.getToday());
            sel.setBankId(getBankId());
            sel.setDefCurr(getDefCurr());
            sel.setImageURL(getImageUrl());
            sel.setImageDir(getImgBaseDir());
            sel.setDbUsed(getDbUsed());
            model.addAttribute("sel", sel);
            model.addAttribute("eodParams", eodParams);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depseod";
    }

    @PostMapping(value = "/DepsEod.action", params = "action=Confirm")
    public String confirm(DepsEodParams eodParams, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            eodParams.setDbUsed(getDbUsed());
            eodParams.setEod_oper(getUserId());
            eodUtil.EODSetEodParams(conn, eodParams);
            String eodFlag = eodParams.getEod_flag();
            DepsSelector sel = new DepsSelector();
            sel.setEod_status(eodFlag);
            if (eodFlag.equals("N")) {
                int detailCount = deUtil.GetDepsEodStatusSummary(conn, sel);
                if (detailCount > 0) {
                    eodUtil.EODPerform_EOD(conn, eodParams);
                } else {
                    eodUtil.EODUpdateControl(conn, eodParams);
                }
                eodUtil.EODCloseTheDay(conn, eodParams);
            }
            model.addAttribute("eodParams", eodParams);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/depseoddone";
    }

    @PostMapping(value = "/DepsEod.action", params = "action=UnDo")
    public String undo(DepsEodParams eodParams, Model model) {
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
        return "deposits/depseoddone";
    }
}
