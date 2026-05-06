package com.webfiche.checkpoint.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;

@ControllerAdvice
public class GlobalModelAdvice {

    @Autowired private UserSession userSession;

    @ModelAttribute
    public void addCommon(Model model) {
        if (!userSession.isLoggedIn()) return;
        model.addAttribute("user", userSession.getUser());
        ProductSelector myProds = userSession.getMyProds();
        if (myProds != null) {
            model.addAttribute("productRows", myProds.getProductrows());
            model.addAttribute("menuGroups", buildMenuGroups(myProds.getProductrows()));
        }
    }

    private List<MenuGroup> buildMenuGroups(ProductDetail[] rows) {
        List<MenuGroup> groups = new ArrayList<>();
        MenuGroup current = null;
        for (ProductDetail row : rows) {
            String menuId = row.getProduct_menu_id().trim();
            String funcId = row.getProduct_menu_func_id().trim();
            String link   = row.getProduct_pmf_link().trim();
            if (menuId.isEmpty()) continue;
            if (funcId.isEmpty()) {
                if (!link.equals("NA") && !link.isEmpty()) {
                    current = new MenuGroup(row.getProduct_pmf_desc(), row.getProduct_id(), menuId);
                    groups.add(current);
                }
            } else if (current != null && !link.equals("NA") && !link.isEmpty()) {
                current.getFunctions().add(new MenuFunction(
                    row.getProduct_pmf_desc(), row.getProduct_id(), menuId, funcId));
            }
        }
        return groups;
    }

    public static class MenuGroup {
        private final String desc, productId, menuId;
        private final List<MenuFunction> functions = new ArrayList<>();
        MenuGroup(String desc, String productId, String menuId) {
            this.desc = desc; this.productId = productId; this.menuId = menuId;
        }
        public String getDesc() { return desc; }
        public String getProductId() { return productId; }
        public String getMenuId() { return menuId; }
        public List<MenuFunction> getFunctions() { return functions; }
    }

    public static class MenuFunction {
        private final String desc, productId, menuId, funcId;
        MenuFunction(String desc, String productId, String menuId, String funcId) {
            this.desc = desc; this.productId = productId;
            this.menuId = menuId; this.funcId = funcId;
        }
        public String getDesc() { return desc; }
        public String getProductId() { return productId; }
        public String getMenuId() { return menuId; }
        public String getFuncId() { return funcId; }
    }
}
