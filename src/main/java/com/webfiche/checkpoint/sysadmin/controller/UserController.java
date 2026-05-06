package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;
import com.webfiche.checkpoint.sysadmin.beans.UserDetail;
import com.webfiche.checkpoint.sysadmin.beans.UserSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadProdUtil;
import com.webfiche.checkpoint.sysadmin.service.SysadUserUtil;

@Controller
public class UserController extends SysadBaseController {

    @Autowired private SysadUserUtil usUtil;
    @Autowired private SysadProdUtil prUtil;

    @GetMapping("/User.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserSelector sel = new UserSelector();
            sel.setDbUsed(getDbUsed());
            ArrayList<String> userList = usUtil.GetUsersList(conn, "");
            sel.setUserList(userList);
            TreeMap<String, String> prodNames = prUtil.GetProductsNameList(conn);
            ProductSelector prodSel = new ProductSelector();
            prodSel.setProdNames(prodNames);
            model.addAttribute("sel", sel);
            model.addAttribute("prodSel", prodSel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/user";
    }

    @GetMapping(value = "/User.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String userFrom,
                       @RequestParam(defaultValue = "") String userTo,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserSelector sel = new UserSelector();
            sel.setDbUsed(getDbUsed());
            sel.setUser_id_from(userFrom);
            sel.setUser_id_to(userTo);
            sel.setActionFlag("view");
            usUtil.GetUserRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/userinq";
    }

    @GetMapping(value = "/User.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            TreeMap<String, String> prodNames = prUtil.GetProductsNameList(conn);
            ProductSelector prodSel = new ProductSelector();
            prodSel.setProdNames(prodNames);
            model.addAttribute("userDetail", new UserDetail());
            model.addAttribute("prodSel", prodSel);
            model.addAttribute("actionMode", "Add");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/userAMD";
    }

    @GetMapping(value = "/User.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String userId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserSelector sel = new UserSelector();
            sel.setDbUsed(getDbUsed());
            usUtil.GetUser(conn, sel, userId, "");
            UserDetail ud = sel.getUserrows().length > 0 ? sel.getUserrows()[0] : new UserDetail();
            TreeMap<String, String> prodNames = prUtil.GetProductsNameList(conn);
            ProductSelector prodSel = new ProductSelector();
            prodSel.setProdNames(prodNames);
            model.addAttribute("userDetail", ud);
            model.addAttribute("prodSel", prodSel);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/userAMD";
    }

    @PostMapping(value = "/User.action", params = "action=Add")
    public String add(UserDetail userDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            usUtil.InsertUpdateUser(conn, userDetail, userDetail.getUser_id(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/userAMD";
        }
        return "redirect:/User.action";
    }

    @PostMapping(value = "/User.action", params = "action=Update")
    public String update(UserDetail userDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            usUtil.InsertUpdateUser(conn, userDetail, userDetail.getUser_id(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/userAMD";
        }
        return "redirect:/User.action";
    }

    @PostMapping(value = "/User.action", params = "action=Delete")
    public String delete(@RequestParam String userId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            UserDetail ud = new UserDetail();
            ud.setUser_id(userId);
            ud.setDbUsed(getDbUsed());
            usUtil.DeleteUser(conn, ud, userId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/User.action";
    }
}
