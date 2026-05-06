package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.UserSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadUserUtil;

@Controller
public class UserAuditController extends SysadBaseController {

    @Autowired private SysadUserUtil usUtil;

    @GetMapping("/UserAudit.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserSelector sel = new UserSelector();
            sel.setDbUsed(getDbUsed());
            usUtil.GetUserRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/useraudit";
    }

    @GetMapping(value = "/UserAudit.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String userFrom,
                       @RequestParam(defaultValue = "") String userTo,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserSelector sel = new UserSelector();
            sel.setDbUsed(getDbUsed());
            sel.setUser_id_from(userFrom);
            sel.setUser_id_to(userTo);
            usUtil.GetUserRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/useraudit";
    }
}
