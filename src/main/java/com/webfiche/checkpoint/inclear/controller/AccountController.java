package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.AccountDetail;
import com.webfiche.checkpoint.inclear.beans.AccountSelector;
import com.webfiche.checkpoint.inclear.service.InclAcctUtil;

@Controller
public class AccountController extends InclearBaseController {

    @Autowired private InclAcctUtil acUtil;

    private AccountSelector buildSelector() {
        AccountSelector sel = new AccountSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        return sel;
    }

    @GetMapping("/Account.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountSelector sel = buildSelector();
            acUtil.GetAccountRows(conn, sel);
            ArrayList<String> acctList = acUtil.GetAccountList(conn);
            sel.setAcctList(acctList);
            TreeMap<String, String> creditorList = acUtil.GetCreditorList(conn);
            model.addAttribute("sel", sel);
            model.addAttribute("creditorList", creditorList);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/account";
    }

    @GetMapping(value = "/Account.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountSelector sel = buildSelector();
            sel.setAccount_num_from(fromAccount);
            sel.setAccount_num_to(toAccount);
            acUtil.GetAccountRows(conn, sel);
            ArrayList<String> acctList = acUtil.GetAccountList(conn);
            sel.setAcctList(acctList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/accountinq";
    }

    @GetMapping(value = "/Account.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        AccountDetail ad = new AccountDetail();
        ad.setDbUsed(getDbUsed());
        ad.setAppl_date(getApplDate());
        ad.setBankId(getBankId());
        ad.setDefCurr(getDefCurr());
        model.addAttribute("acctDetail", ad);
        model.addAttribute("actionMode", "New");
        model.addAttribute("user", userSession.getUser());
        return "inclear/accountAMD";
    }

    @GetMapping(value = "/Account.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String acctNum,
                             @RequestParam(defaultValue = "") String actionFlag,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountSelector sel = buildSelector();
            acUtil.GetAccountRows(conn, sel, acctNum);
            AccountDetail ad = sel.getModifyRow() != null ? sel.getModifyRow() : new AccountDetail();
            ad.setDbUsed(getDbUsed());
            model.addAttribute("acctDetail", ad);
            model.addAttribute("actionMode", actionFlag.isEmpty() ? "Modify" : actionFlag);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/accountAMD";
    }

    @PostMapping(value = "/Account.action", params = "action=Add")
    public String add(AccountDetail acctDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        acctDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            acUtil.InsertUpdateAcct(conn, acctDetail, getUserId(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("acctDetail", acctDetail);
            model.addAttribute("actionMode", "New");
            model.addAttribute("user", userSession.getUser());
            return "inclear/accountAMD";
        }
        return "redirect:/Account.action";
    }

    @PostMapping(value = "/Account.action", params = "action=Update")
    public String update(AccountDetail acctDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        acctDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            acUtil.InsertUpdateAcct(conn, acctDetail, getUserId(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("acctDetail", acctDetail);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
            return "inclear/accountAMD";
        }
        return "redirect:/Account.action";
    }

    @PostMapping(value = "/Account.action", params = "action=Delete")
    public String delete(@RequestParam(defaultValue = "") String acctNum, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            AccountDetail ad = new AccountDetail();
            ad.setDbUsed(getDbUsed());
            ad.setAccount_num(acctNum);
            acUtil.DeleteAccount(conn, ad, getUserId());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Account.action";
    }
}
