package com.webfiche.checkpoint.deposits.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.deposits.beans.PayerDetail;
import com.webfiche.checkpoint.deposits.beans.PayerSelector;
import com.webfiche.checkpoint.deposits.service.DepsPayerUtil;

@Controller
public class DepositsPayerController extends DepositsBaseController {

    @Autowired private DepsPayerUtil dpUtil;

    private PayerSelector buildSelector(Connection conn) throws Exception {
        PayerSelector sel = new PayerSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setDbTable("payers");
        TreeMap<String, String> payersList = dpUtil.GetPayerNamesList(conn, sel);
        sel.setPayersList(payersList);
        ArrayList<String> abaList = dpUtil.GetPayerAbaList(conn, sel);
        sel.setPayerAbaList(abaList);
        ArrayList<String> acctList = dpUtil.GetPayerAcctList(conn, sel);
        sel.setPayerAcctList(acctList);
        return sel;
    }

    @GetMapping("/DepositsPayer.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = buildSelector(conn);
            dpUtil.GetPayerRows(conn, sel, "");
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payers";
    }

    @GetMapping(value = "/DepositsPayer.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String payerAbaFrom,
                       @RequestParam(defaultValue = "") String payerAbaTo,
                       @RequestParam(defaultValue = "") String payerAcctFrom,
                       @RequestParam(defaultValue = "") String payerAcctTo,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = buildSelector(conn);
            sel.setPayerAbaFrom(payerAbaFrom);
            sel.setPayerAbaTo(payerAbaTo);
            sel.setPayerAcctFrom(payerAcctFrom);
            sel.setPayerAcctTo(payerAcctTo);
            dpUtil.GetPayerRows(conn, sel, "");
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payers";
    }

    @GetMapping(value = "/DepositsPayer.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = buildSelector(conn);
            PayerDetail pd = new PayerDetail();
            pd.setAppl_date(getApplDate());
            pd.setBankId(getBankId());
            pd.setDefCurr(getDefCurr());
            pd.setDbUsed(getDbUsed());
            model.addAttribute("sel", sel);
            model.addAttribute("payerDetail", pd);
            model.addAttribute("actionMode", "New");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payersAMD";
    }

    @GetMapping(value = "/DepositsPayer.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String payerAba,
                             @RequestParam(defaultValue = "") String payerAcct,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerSelector sel = buildSelector(conn);
            sel.setPayerAbaFrom(payerAba);
            sel.setPayerAcctFrom(payerAcct);
            dpUtil.GetPayerRows(conn, sel, payerAcct);
            PayerDetail pd = sel.getPayerRows().length > 0 ? sel.getPayerRows()[0] : new PayerDetail();
            pd.setAppl_date(getApplDate());
            pd.setBankId(getBankId());
            pd.setDefCurr(getDefCurr());
            pd.setDbUsed(getDbUsed());
            model.addAttribute("sel", sel);
            model.addAttribute("payerDetail", pd);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "deposits/payersAMD";
    }

    @PostMapping(value = "/DepositsPayer.action", params = "action=Add")
    public String add(PayerDetail payerDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        payerDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            dpUtil.AddUpdatePayerAcct(conn, payerDetail, getOurAba(), getUserId(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("payerDetail", payerDetail);
            model.addAttribute("actionMode", "New");
            model.addAttribute("user", userSession.getUser());
            return "deposits/payersAMD";
        }
        return "redirect:/DepositsPayer.action";
    }

    @PostMapping(value = "/DepositsPayer.action", params = "action=Update")
    public String update(PayerDetail payerDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        payerDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            dpUtil.AddUpdatePayerAcct(conn, payerDetail, getOurAba(), getUserId(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("payerDetail", payerDetail);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
            return "deposits/payersAMD";
        }
        return "redirect:/DepositsPayer.action";
    }

    @PostMapping(value = "/DepositsPayer.action", params = "action=Delete")
    public String delete(@RequestParam(defaultValue = "") String payerAba,
                         @RequestParam(defaultValue = "") String payerAcct,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PayerDetail pd = new PayerDetail();
            pd.setDbUsed(getDbUsed());
            pd.setPayeraba(payerAba);
            pd.setPayeracct(payerAcct);
            dpUtil.DeletePayerAcct(conn, pd, getUserId());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/DepositsPayer.action";
    }
}
