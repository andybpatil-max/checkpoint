package com.webfiche.checkpoint.config;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.webfiche.checkpoint.sysadmin.beans.GroupSelector;
import com.webfiche.checkpoint.sysadmin.beans.ProductDetail;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;
import com.webfiche.checkpoint.sysadmin.beans.UserDetail;
import com.webfiche.checkpoint.sysadmin.beans.UserSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadProdUtil;
import com.webfiche.checkpoint.sysadmin.service.SysadUserUtil;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired private UserSession userSession;
    @Autowired private SysadUserUtil sysadUserUtil;
    @Autowired private SysadProdUtil sysadProdUtil;
    @Autowired private DataSource dataSource;
    @Autowired private AppProperties appProperties;
    @Autowired private AppStartupService appStartupService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String username = authentication.getName();
        try (Connection conn = dataSource.getConnection()) {
            // Load UserDetail from DB
            UserSelector userSelector = new UserSelector();
            sysadUserUtil.GetUser(conn, userSelector, username, "");
            UserDetail[] rows = userSelector.getUserrows();
            if (rows == null || rows.length == 0) {
                setDefaultTargetUrl("/Menu.action");
                super.onAuthenticationSuccess(request, response, authentication);
                return;
            }
            UserDetail ud = rows[0];

            // Build User bean
            com.webfiche.checkpoint.beans.User user = new com.webfiche.checkpoint.beans.User();
            user.setUserId(ud.getUser_id());
            user.setUserFName(ud.getUser_fname());
            user.setUserLName(ud.getUser_lname());
            user.setNodeName(appProperties.getNodeName());
            String applDate = appProperties.getApplDate();
            if (applDate != null && applDate.length() >= 8) {
                user.setApplDate(applDate.substring(4, 6) + "/" + applDate.substring(6, 8) + "/" + applDate.substring(0, 4));
            } else {
                user.setApplDate(applDate != null ? applDate : "");
            }
            user.setActionFlag("P");
            user.setUserProd(" ");
            user.setUserMenu(" ");

            // Build authorized product grid (port of LogonAction.loggedIn())
            ProductSelector myProds = buildAuthorizedProducts(conn, ud, username);
            userSession.setUser(user);
            userSession.setMyProds(myProds);

            // Record successful login
            try {
                sysadUserUtil.UpdateUserLastSuccessLogin(conn, username);
            } catch (Exception e) {
                System.out.println("LoginSuccessHandler: could not update login timestamp: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("LoginSuccessHandler: DB error building session for " + username + ": " + e);
        }

        setDefaultTargetUrl("/Menu.action");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private ProductSelector buildAuthorizedProducts(Connection conn, UserDetail ud, String userId) {
        String[] pIds = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};

        ProductSelector allProducts = appStartupService.getAllProducts();
        ProductSelector myProds = new ProductSelector();
        myProds.clearRows();
        myProds.setUserId(userId);

        // Call setPMFValues on every product so links/params are set
        ProductDetail[] prodDetails = allProducts.getProductrows();
        for (ProductDetail pd : prodDetails) {
            pd.setPMFValues();
        }

        ArrayList<String> userGroups = ud.getUserGroups();
        GroupSelector myGroups = new GroupSelector();
        ArrayList<ProductDetail> tempProds = new ArrayList<>();
        int index = 0;

        for (String userGroup : userGroups) {
            if (userGroup == null) {
                userGroup = " ";
            } else {
                userGroup = userGroup.trim();
            }
            if (!userGroup.equals(" ") && !userGroup.isEmpty()) {
                try {
                    String groupId = pIds[index] + userGroup;
                    ArrayList<?> groupRow = sysadProdUtil.GetAGroupRow(conn, groupId, myGroups);

                    String menuFuncStr = "";
                    if (groupRow.size() > 0) {
                        menuFuncStr = (String) groupRow.get(2);
                        if (menuFuncStr == null) menuFuncStr = "";
                    }

                    if (menuFuncStr.length() > 1) {
                        String prod1 = ((String) groupRow.get(0)).substring(0, 1);
                        String temp = "  " + menuFuncStr;
                        int l = 1;
                        for (int j = 0; j < temp.length(); j++) {
                            String menu1 = temp.substring(j, j + 1);
                            String func1 = temp.substring(j + 1, j + 2);
                            for (ProductDetail pd : prodDetails) {
                                String tempProd = pd.getProduct_id().isEmpty() ? " " : pd.getProduct_id();
                                String tempMenu = pd.getProduct_menu_id().isEmpty() ? " " : pd.getProduct_menu_id();
                                String tempFunc = pd.getProduct_menu_func_id().isEmpty() ? " " : pd.getProduct_menu_func_id();
                                if (tempProd.equals(prod1) && tempMenu.equals(menu1) && tempFunc.equals(func1)) {
                                    if (tempFunc.equals(" ") || tempFunc.isEmpty()) {
                                        pd.setProduct_pmf_link(pd.getProduct_pmf_link_o().trim()
                                                + "?prod=" + tempProd + "&menu=" + tempMenu);
                                    } else {
                                        String link = pd.getProduct_pmf_link_o();
                                        String param = "";
                                        if (link.indexOf("?") > 0) {
                                            if (link.length() > link.indexOf("=")) {
                                                param = link.substring(link.indexOf("=") + 1);
                                            }
                                            link = link.substring(0, link.indexOf("?"));
                                            pd.setProduct_pmf_param(param);
                                        }
                                        pd.setProduct_pmf_link(link);
                                    }
                                    if (!tempProd.equals(" ") && !tempMenu.equals(" ") && tempFunc.equals(" ")) {
                                        pd.setMenuRank(l++);
                                    }
                                    tempProds.add(pd);
                                }
                            }
                            j++;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("LoginSuccessHandler: GetAGroupRow error for index " + index + ": " + e.getMessage());
                }
            }
            index++;
            if (index >= pIds.length) break;
        }

        myProds.setProductrows(tempProds);
        System.out.println("LoginSuccessHandler: authorized products for " + userId + ": " + tempProds.size());
        return myProds;
    }
}
