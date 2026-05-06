package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.DbDetail;
import com.webfiche.checkpoint.sysadmin.beans.DbSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadDbUtil;

@Controller
public class DbTableController extends SysadBaseController {

    @Autowired private SysadDbUtil dbUtil;

    @GetMapping("/DbTable.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DbSelector sel = new DbSelector();
            sel.setDbUsed(getDbUsed());
            dbUtil.GetTableRows(conn, sel, getDbUsed());
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/dbTables";
    }

    @GetMapping(value = "/DbTable.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("dbDetail", new DbDetail());
        model.addAttribute("actionMode", "Add");
        model.addAttribute("user", userSession.getUser());
        return "sysadmin/dbTablesAMD";
    }

    @GetMapping(value = "/DbTable.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String tableId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DbSelector sel = new DbSelector();
            sel.setDbUsed(getDbUsed());
            dbUtil.GetTableRows(conn, sel, tableId);
            DbDetail dd = sel.getDbRows().length > 0 ? sel.getDbRows()[0] : new DbDetail();
            model.addAttribute("dbDetail", dd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/dbTablesAMD";
    }

    @PostMapping(value = "/DbTable.action", params = "action=Add")
    public String add(DbDetail dbDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            dbUtil.InsertUpdateTable(conn, dbDetail, getDbUsed(), userId, 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/dbTablesAMD";
        }
        return "redirect:/DbTable.action";
    }

    @PostMapping(value = "/DbTable.action", params = "action=Update")
    public String update(DbDetail dbDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            dbUtil.InsertUpdateTable(conn, dbDetail, getDbUsed(), userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/dbTablesAMD";
        }
        return "redirect:/DbTable.action";
    }

    @PostMapping(value = "/DbTable.action", params = "action=Delete")
    public String delete(@RequestParam String tableId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            DbDetail dd = new DbDetail();
            dd.setDbTable(tableId);
            dd.setDbUsed(getDbUsed());
            dbUtil.DeleteTable(conn, dd);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/DbTable.action";
    }
}
