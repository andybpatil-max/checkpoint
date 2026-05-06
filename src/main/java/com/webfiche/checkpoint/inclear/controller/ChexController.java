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
import com.webfiche.checkpoint.inclear.beans.ChexDetail;
import com.webfiche.checkpoint.inclear.beans.ChexSelector;
import com.webfiche.checkpoint.inclear.service.InclChexUtil;

@Controller
public class ChexController extends InclearBaseController {

    @Autowired private InclChexUtil chUtil;

    private ChexSelector buildSelector() {
        ChexSelector sel = new ChexSelector();
        sel.setDbUsed(getDbUsed());
        sel.setAppl_date(getApplDate());
        sel.setBankId(getBankId());
        sel.setDefCurr(getDefCurr());
        sel.setImageURL(getImageUrl());
        sel.setImageDir(getImgBaseDir());
        sel.setDbTable("incl_chex");
        return sel;
    }

    private void loadLists(Connection conn, ChexSelector sel) throws Exception {
        ArrayList<String> acctList = chUtil.GetChexAccountList(conn, "incl_chex");
        sel.setAcctList(acctList);
        TreeMap<String, String> rejReasons = chUtil.GetRejectReasons(conn, "rejectreasons");
        sel.setRejReasons(rejReasons);
        TreeMap<String, String> retReasons = chUtil.GetReturnReasons(conn, "returnreasons");
        sel.setRetReasons(retReasons);
        TreeMap<String, String> chexStatus = chUtil.GetChexStatus(conn, "chexstatus");
        sel.setChexStatus(chexStatus);
    }

    @GetMapping("/Chex.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            loadLists(conn, sel);
            chUtil.GetChexStatusSummaryRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chex";
    }

    @GetMapping(value = "/Chex.action", params = "action=View")
    public String view(@RequestParam(defaultValue = "") String fromAccount,
                       @RequestParam(defaultValue = "") String toAccount,
                       @RequestParam(defaultValue = "") String fromDate,
                       @RequestParam(defaultValue = "") String toDate,
                       @RequestParam(defaultValue = "") String fromCheck,
                       @RequestParam(defaultValue = "") String toCheck,
                       @RequestParam(defaultValue = "") String chexStatus,
                       Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            sel.setCh_from_account(fromAccount);
            sel.setCh_to_account(toAccount);
            sel.setCh_from_date(fromDate);
            sel.setCh_to_date(toDate);
            sel.setCh_from_check(fromCheck);
            sel.setCh_to_check(toCheck);
            sel.setCh_check_status(chexStatus);
            loadLists(conn, sel);
            chUtil.GetChexRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexinq";
    }

    @GetMapping(value = "/Chex.action", params = "action=SummaryView")
    public String summary(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            loadLists(conn, sel);
            chUtil.GetChexStatusSummaryRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexsummary";
    }

    @GetMapping(value = "/Chex.action", params = "action=Authorize")
    public String authorize(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            loadLists(conn, sel);
            chUtil.GetChexRowsByUser(conn, sel, getUserId());
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexauth";
    }

    @GetMapping(value = "/Chex.action", params = "action=Fraud")
    public String fraud(@RequestParam(defaultValue = "") String checkStatus, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            loadLists(conn, sel);
            if (!checkStatus.isEmpty()) {
                chUtil.GetChexRows(conn, sel, checkStatus);
            } else {
                chUtil.GetChexRows(conn, sel);
            }
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexfraud";
    }

    @GetMapping(value = "/Chex.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String acctNum,
                             @RequestParam(defaultValue = "") String checkNum,
                             @RequestParam(defaultValue = "") String uniqueIsn,
                             Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            ChexSelector sel = buildSelector();
            chUtil.GetChexRows(conn, sel, acctNum, checkNum, uniqueIsn);
            ChexDetail cd = sel.getCheckrows().length > 0 ? sel.getCheckrows()[0] : new ChexDetail();
            loadLists(conn, sel);
            model.addAttribute("chexDetail", cd);
            model.addAttribute("sel", sel);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "inclear/chexmodify";
    }

    @PostMapping(value = "/Chex.action", params = "action=Update")
    public String update(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.InsertUpdateChex(conn, chexDetail, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chexDetail", chexDetail);
            model.addAttribute("actionMode", "Modify");
            model.addAttribute("user", userSession.getUser());
            return "inclear/chexmodify";
        }
        return "redirect:/Chex.action";
    }

    @PostMapping(value = "/Chex.action", params = "action=AuthAll")
    public String authAll(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.AuthRejAllChex(conn, getDbUsed(), getApplDate(), getUserId(), 1, "AuthAll");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Authorize";
    }

    @PostMapping(value = "/Chex.action", params = "action=RejectAll")
    public String rejectAll(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.AuthRejAllChex(conn, getDbUsed(), getApplDate(), getUserId(), 2, "RejectAll");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Authorize";
    }

    @PostMapping(value = "/Chex.action", params = "action=AuthOne")
    public String authOne(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.AuthRejectChex(conn, chexDetail, getUserId(), 1, "AuthOne");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Authorize";
    }

    @PostMapping(value = "/Chex.action", params = "action=RejectOne")
    public String rejectOne(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.AuthRejectChex(conn, chexDetail, getUserId(), 2, "RejectOne");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Authorize";
    }

    @PostMapping(value = "/Chex.action", params = "action=FraudOne")
    public String fraudOne(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.GenuineFraudChex(conn, chexDetail, getUserId(), 2, "FraudOne");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Fraud";
    }

    @PostMapping(value = "/Chex.action", params = "action=GenuineOne")
    public String genuineOne(ChexDetail chexDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            chUtil.GenuineFraudChex(conn, chexDetail, getUserId(), 1, "GenuineOne");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/Chex.action?action=Fraud";
    }
}
