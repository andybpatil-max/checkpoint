package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.webfiche.checkpoint.sysadmin.beans.ControlDetail;
import com.webfiche.checkpoint.sysadmin.beans.ControlSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadControlUtil;

@Controller
public class ControlController extends SysadBaseController {

    @Autowired private SysadControlUtil ctlUtil;

    @GetMapping("/Control.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ControlSelector sel = new ControlSelector();
            sel.setDbUsed(getDbUsed());
            ctlUtil.GetControlRow(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/control";
    }

    @PostMapping(value = "/Control.action", params = "action=Update")
    public String update(ControlDetail controlDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            ctlUtil.InsertUpdateControl(conn, controlDetail, userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/control";
        }
        return "redirect:/Control.action";
    }
}
