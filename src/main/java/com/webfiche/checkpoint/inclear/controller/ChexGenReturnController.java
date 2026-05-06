package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;
import com.webfiche.checkpoint.inclear.service.InclReturnChexUtil;

@Controller
public class ChexGenReturnController extends InclearBaseController {

    @Autowired private InclChexUtil chUtil;
    @Autowired private InclReturnChexUtil genReturns;

    @GetMapping("/ChexGenReturn.action")
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
        return "inclear/chexgenreturn";
    }

    @PostMapping(value = "/ChexGenReturn.action", params = "action=GenReturns")
    public String genReturns(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = new ChexSelector();
            sel.setDbUsed(getDbUsed());
            sel.setAppl_date(getApplDate());
            sel.setBankId(getBankId());
            sel.setDefCurr(getDefCurr());
            sel.setImageURL(getImageUrl());
            sel.setImageDir(getImgBaseDir());
            sel.setDbTable("incl_chex");
            ArrayList<String> histList = chUtil.GetChexHistList(conn, getDbUsed(), "incl_chex_history");
            sel.setHistList(histList);
            genReturns.ChexReturn(conn, getDbUsed(), sel, getUserId());
            chUtil.GetRetHistRows(conn, histList, sel);
            TreeMap<String, String> retReasons = chUtil.GetReturnReasons(conn, "returnreasons");
            sel.setRetReasons(retReasons);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexgenreturn";
    }
}
