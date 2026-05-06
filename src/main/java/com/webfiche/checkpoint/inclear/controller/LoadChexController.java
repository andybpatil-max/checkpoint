package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.LoadstatsSelector;
import com.webfiche.checkpoint.inclear.service.UploadMOChex;
import com.webfiche.checkpoint.inclear.service.UploadNYChex;
import com.webfiche.checkpoint.inclear.service.viewMOStatsDb;
import com.webfiche.checkpoint.inclear.service.viewStatsDb;
import com.webfiche.checkpoint.sysadmin.beans.ControlDetail;
import com.webfiche.checkpoint.sysadmin.beans.ControlSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadControlUtil;

@Controller
public class LoadChexController extends InclearBaseController {

    @Autowired private SysadControlUtil ctlUtil;
    @Autowired private UploadMOChex loadMOChex;
    @Autowired private UploadNYChex loadNYChex;
    @Autowired private viewStatsDb vdb;
    @Autowired private viewMOStatsDb movdb;

    @GetMapping("/LoadChex.action")
    public String uploadData(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ControlSelector ctlSel = new ControlSelector();
            ctlUtil.GetControlRow(conn, ctlSel, "A");
            ControlDetail ctlDetail = ctlSel.getARow();
            ControlSelector ctlSelC = new ControlSelector();
            ctlUtil.GetControlRow(conn, ctlSelC, "C");
            ControlDetail ctlDetailC = ctlSelC.getARow();
            model.addAttribute("ctlDetail", ctlDetail);
            model.addAttribute("ctlDetailC", ctlDetailC);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexupload";
    }

    @PostMapping(value = "/LoadChex.action", params = "action=LoadFile")
    public String loadFile(ControlDetail ctlDetail, ControlDetail ctlDetailC,
                           @RequestParam(defaultValue = "") String fileToLoad,
                           Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            String applDate = ctlDetail.getApplDate();
            String bankId = ctlDetail.getBankId();
            String ourAba = ctlDetail.getOurAba();
            String imgBaseDir = ctlDetail.getImgBaseDir();
            String locOutputDir = ctlDetail.getLocOutputDir();
            String dataDir = imgBaseDir + "incl/" + applDate.substring(0, 4) + "/"
                    + applDate.substring(4, 6) + "/" + applDate.substring(6, 8) + "/";
            String outDir = locOutputDir + "gps/";
            String reportDir = locOutputDir + "incl/";
            LoadstatsSelector statSel = new LoadstatsSelector();
            int loadResult;
            if ("BNPMO".equals(bankId)) {
                loadResult = loadMOChex.MontChexLoad(conn, getDbUsed(), ourAba,
                        applDate, dataDir, outDir, reportDir, fileToLoad, 1, "LoadFile", getUserId());
                if (loadResult == 1) {
                    movdb.getLoadStats(conn, statSel);
                    model.addAttribute("statSel", statSel);
                    model.addAttribute("user", userSession.getUser());
                    return "inclear/viewMOStats";
                }
            } else {
                loadResult = loadNYChex.UpLoadMICR(conn, getDbUsed(), applDate,
                        dataDir, reportDir, fileToLoad, 1, "LoadFile", getUserId());
                if (loadResult == 1) {
                    vdb.getLoadStats(conn, statSel);
                    model.addAttribute("statSel", statSel);
                    model.addAttribute("user", userSession.getUser());
                    return "inclear/viewStats";
                }
            }
            model.addAttribute("error", "Load failed");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/LoadChex.action";
    }

    @GetMapping(value = "/LoadChex.action", params = "action=ViewStats")
    public String viewStats(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            LoadstatsSelector statSel = new LoadstatsSelector();
            vdb.getLoadStats(conn, statSel);
            model.addAttribute("statSel", statSel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/viewStats";
    }
}
