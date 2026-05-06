package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.ChexDetail;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class ChexReturnController extends InclearBaseController {

    @Autowired private InclChexUtil chUtil;

    @GetMapping("/ChexReturn.action")
    public String returns(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        ChexSelector sel = new ChexSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setImageURL(getImageUrl());
        sel.setImageDir(getImgBaseDir() + "incl/");
        sel.setDbTable("incl_chex");
        try (Connection conn = openConnection()) {
            ArrayList<String> histList = chUtil.GetChexHistList(conn, getDbUsed(), "incl_chex_history");
            sel.setHistList(histList);
            try { chUtil.GetRetHistRows(conn, histList, sel); } catch (Exception ignored) {}
            TreeMap<String, String> retReasons = chUtil.GetReturnReasons(conn, "returnreasons");
            sel.setRetReasons(retReasons);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("sel", sel);
        model.addAttribute("user", userSession.getUser());
        return "inclear/chexreturn";
    }

    @GetMapping(value = "/ChexReturn.action", params = "action=Detail")
    public String detail(@RequestParam(defaultValue = "") String acctNum,
                         @RequestParam(defaultValue = "") String checkNum,
                         @RequestParam(defaultValue = "") String uniqueIsn,
                         @RequestParam(defaultValue = "") String procDate,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = new ChexSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            chUtil.GetHistRows(conn, procDate, acctNum, checkNum, uniqueIsn, sel);
            ChexDetail cd = sel.getCheckrows().length > 0 ? sel.getCheckrows()[0] : new ChexDetail();
            TreeMap<String, String> retReasons = chUtil.GetReturnReasons(conn, "returnreasons");
            sel.setRetReasons(retReasons);
            model.addAttribute("chexDetail", cd);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexreturndetail";
    }

    @PostMapping(value = "/ChexReturn.action", params = "action=Return")
    public String doReturn(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chexDetail.setDbUsed(getDbUsed());
            chUtil.ReturnUndoChex(conn, chexDetail, getApplDate(), getUserId(), "R", "Return");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chexDetail", chexDetail);
            model.addAttribute("user", userSession.getUser());
            return "inclear/chexreturndetail";
        }
        return "redirect:/ChexReturn.action";
    }

    @PostMapping(value = "/ChexReturn.action", params = "action=UndoReturn")
    public String undoReturn(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chexDetail.setDbUsed(getDbUsed());
            chUtil.ReturnUndoChex(conn, chexDetail, getApplDate(), getUserId(), "U", "UndoReturn");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chexDetail", chexDetail);
            model.addAttribute("user", userSession.getUser());
            return "inclear/chexreturndetail";
        }
        return "redirect:/ChexReturn.action";
    }
}
