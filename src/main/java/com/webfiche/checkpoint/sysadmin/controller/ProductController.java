package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadProdUtil;

@Controller
public class ProductController extends SysadBaseController {

    @Autowired private SysadProdUtil prUtil;

    @GetMapping("/Product.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ProductSelector sel = new ProductSelector();
            sel.setDbUsed(getDbUsed());
            prUtil.GetProductRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/product";
    }

    @GetMapping(value = "/Product.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("prodDetail", new ProductDetail());
        model.addAttribute("actionMode", "Add");
        model.addAttribute("user", userSession.getUser());
        return "sysadmin/productAMD";
    }

    @GetMapping(value = "/Product.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String prodId,
                             @RequestParam(defaultValue = "") String menuId,
                             @RequestParam(defaultValue = "") String funcId,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ProductSelector sel = new ProductSelector();
            sel.setDbUsed(getDbUsed());
            sel.setProduct_id_sel(prodId);
            sel.setProduct_menu_id_sel(menuId);
            sel.setProduct_menu_func_id_sel(funcId);
            prUtil.GetProductRows(conn, sel, prodId, menuId, funcId);
            ProductDetail pd = sel.getProductrows().length > 0 ? sel.getProductrows()[0] : new ProductDetail();
            model.addAttribute("prodDetail", pd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/productAMD";
    }

    @PostMapping(value = "/Product.action", params = "action=Add")
    public String add(ProductDetail prodDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            prUtil.InsertUpdateProduct(conn, prodDetail, getDbUsed(), userId, 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/productAMD";
        }
        return "redirect:/Product.action";
    }

    @PostMapping(value = "/Product.action", params = "action=Update")
    public String update(ProductDetail prodDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            prUtil.InsertUpdateProduct(conn, prodDetail, getDbUsed(), userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/productAMD";
        }
        return "redirect:/Product.action";
    }

    @PostMapping(value = "/Product.action", params = "action=Delete")
    public String delete(@RequestParam String prodId, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            ProductDetail pd = new ProductDetail();
            pd.setProduct_id(prodId);
            pd.setDbUsed(getDbUsed());
            prUtil.DeleteProduct(conn, pd, getDbUsed(), userId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Product.action";
    }
}
