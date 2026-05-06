package com.webfiche.checkpoint.inclear.controller;

import java.sql.Connection;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.PosipayDetail;
import com.webfiche.checkpoint.inclear.beans.PosipaySelector;
import com.webfiche.checkpoint.inclear.service.InclPosiUtil;

@Controller
public class PosipayController extends InclearBaseController {

    @Autowired private InclPosiUtil posiUtil;

    private PosipaySelector buildSelector() {
        PosipaySelector sel = new PosipaySelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        return sel;
    }

    @GetMapping("/Posipay.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipaySelector sel = buildSelector();
            posiUtil.GetPosipayRows(conn, sel);
            ArrayList<String> acctList = posiUtil.GetPosiAccountList(conn);
            sel.setAcctList(acctList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/posipay";
    }

    @GetMapping(value = "/Posipay.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromCheck,
                       @RequestParam(defaultValue = "") String toCheck,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipaySelector sel = buildSelector();
            sel.setPp_from_acct(fromAccount);
            sel.setPp_to_acct(toAccount);
            sel.setPp_from_check(fromCheck);
            sel.setPp_to_check(toCheck);
            posiUtil.GetPosipayRows(conn, sel);
            ArrayList<String> acctList = posiUtil.GetPosiAccountList(conn);
            sel.setAcctList(acctList);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/posipayinq";
    }

    @GetMapping(value = "/Posipay.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        PosipayDetail pd = new PosipayDetail();
        pd.setDbUsed(getDbUsed());
        pd.setAppl_date(getApplDate());
        pd.setBankId(getBankId());
        pd.setDefCurr(getDefCurr());
        model.addAttribute("posiDetail", pd);
        model.addAttribute("actionMode", "New");
        model.addAttribute("user", userSession.getUser());
        return "inclear/posipayAMD";
    }

    @GetMapping(value = "/Posipay.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String acctNum,
                             @RequestParam(defaultValue = "") String checkNum,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipaySelector sel = buildSelector();
            posiUtil.GetPosipayRows(conn, sel, acctNum, checkNum);
            PosipayDetail pd = sel.getArow() != null ? sel.getArow() : new PosipayDetail();
            pd.setDbUsed(getDbUsed());
            model.addAttribute("posiDetail", pd);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/posipayAMD";
    }

    @PostMapping(value = "/Posipay.action", params = "action=Add")
    public String add(PosipayDetail posiDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        posiDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            posiUtil.InsertUpdatePosi(conn, posiDetail, getUserId(), 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("posiDetail", posiDetail);
            model.addAttribute("actionMode", "New");
            model.addAttribute("user", userSession.getUser());
            return "inclear/posipayAMD";
        }
        return "redirect:/Posipay.action";
    }

    @PostMapping(value = "/Posipay.action", params = "action=Update")
    public String update(PosipayDetail posiDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        posiDetail.setDbUsed(getDbUsed());
        try (Connection conn = openConnection()) {
            posiUtil.InsertUpdatePosi(conn, posiDetail, getUserId(), 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("posiDetail", posiDetail);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
            return "inclear/posipayAMD";
        }
        return "redirect:/Posipay.action";
    }

    @PostMapping(value = "/Posipay.action", params = "action=Delete")
    public String delete(@RequestParam(defaultValue = "") String acctNum,
                         @RequestParam(defaultValue = "") String checkNum,
                         Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            PosipayDetail pd = new PosipayDetail();
            pd.setDbUsed(getDbUsed());
            pd.setPospay_account_num(acctNum);
            pd.setPospay_check_num(checkNum);
            posiUtil.DeletePosipay(conn, pd, getUserId());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Posipay.action";
    }
}
