package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.RhostDetail;
import com.webfiche.checkpoint.sysadmin.beans.RhostSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadHostUtil;

@Controller
public class RhostsController extends SysadBaseController {

    @Autowired private SysadHostUtil hoUtil;

    @GetMapping("/Rhosts.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            RhostSelector sel = new RhostSelector();
            sel.setDbUsed(getDbUsed());
            hoUtil.GetHostRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/rhosts";
    }

    @GetMapping(value = "/Rhosts.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("rhostDetail", new RhostDetail());
        model.addAttribute("actionMode", "Add");
        model.addAttribute("user", userSession.getUser());
        return "sysadmin/rhostsAMD";
    }

    @GetMapping(value = "/Rhosts.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String hostId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            RhostSelector sel = new RhostSelector();
            sel.setDbUsed(getDbUsed());
            hoUtil.GetHostRows(conn, sel, hostId);
            RhostDetail rd = sel.getRhostrows().length > 0 ? sel.getRhostrows()[0] : new RhostDetail();
            model.addAttribute("rhostDetail", rd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/rhostsAMD";
    }

    @PostMapping(value = "/Rhosts.action", params = "action=Add")
    public String add(RhostDetail rhostDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            hoUtil.InsertUpdateHost(conn, rhostDetail, getDbUsed(), userId, 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/rhostsAMD";
        }
        return "redirect:/Rhosts.action";
    }

    @PostMapping(value = "/Rhosts.action", params = "action=Update")
    public String update(RhostDetail rhostDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            hoUtil.InsertUpdateHost(conn, rhostDetail, getDbUsed(), userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/rhostsAMD";
        }
        return "redirect:/Rhosts.action";
    }

    @PostMapping(value = "/Rhosts.action", params = "action=Delete")
    public String delete(@RequestParam String hostId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            RhostDetail rd = new RhostDetail();
            rd.setRhosts_node_id(hostId);
            rd.setDbUsed(getDbUsed());
            hoUtil.DeleteHost(conn, rd, getDbUsed(), userId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Rhosts.action";
    }
}
