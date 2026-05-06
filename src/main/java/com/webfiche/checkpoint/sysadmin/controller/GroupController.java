package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.GroupSelector;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadProdUtil;

@Controller
public class GroupController extends SysadBaseController {

    @Autowired private SysadProdUtil prUtil;

    @GetMapping("/Group.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ProductSelector prodSel = new ProductSelector();
            prUtil.GetProductRows(conn, prodSel);
            model.addAttribute("prodSel", prodSel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/group";
    }

    @GetMapping(value = "/Group.action", params = "action=New")
    public String newForm(@RequestParam(defaultValue = "") String prodId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            GroupSelector sel = new GroupSelector();
            sel.setDbUsed(getDbUsed());
            if (!prodId.isEmpty()) {
                prUtil.GetGroupRows(conn, prodId, sel);
            }
            model.addAttribute("groupSel", sel);
            model.addAttribute("prodId", prodId);
            model.addAttribute("actionMode", "Add");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/groupAMD";
    }

    @GetMapping(value = "/Group.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String prodId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            GroupSelector sel = new GroupSelector();
            sel.setDbUsed(getDbUsed());
            prUtil.GetGroupRows(conn, prodId, sel);
            model.addAttribute("groupSel", sel);
            model.addAttribute("prodId", prodId);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/groupAMD";
    }

    @PostMapping(value = "/Group.action", params = "action=Add")
    public String add(@RequestParam String prodId,
                      @RequestParam String groupId,
                      @RequestParam(defaultValue = "") String pmfList,
                      Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            prUtil.InsertUpdateGroup(conn, prodId, groupId, pmfList, userId, getDbUsed(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Group.action";
    }

    @PostMapping(value = "/Group.action", params = "action=Update")
    public String update(@RequestParam String prodId,
                         @RequestParam String groupId,
                         @RequestParam(defaultValue = "") String pmfList,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            prUtil.InsertUpdateGroup(conn, prodId, groupId, pmfList, userId, getDbUsed(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Group.action";
    }

    @PostMapping(value = "/Group.action", params = "action=Delete")
    public String delete(@RequestParam String prodId,
                         @RequestParam String groupId,
                         @RequestParam(defaultValue = "") String pmfList,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            prUtil.DeleteGroup(conn, prodId, groupId, pmfList, userId, getDbUsed());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Group.action";
    }
}
