package com.webfiche.checkpoint.sysadmin.controller;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.sysadmin.beans.CalendarDetail;
import com.webfiche.checkpoint.sysadmin.beans.CalendarSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadCalendarUtil;

@Controller
public class CalendarController extends SysadBaseController {

    @Autowired private SysadCalendarUtil calUtil;

    @GetMapping("/Calendar.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            CalendarSelector sel = new CalendarSelector();
            sel.setDbUsed(getDbUsed());
            calUtil.GetHolidayRows(conn, sel);
            model.addAttribute("sel", sel);
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/calendar";
    }

    @GetMapping(value = "/Calendar.action", params = "action=New")
    public String newForm(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        model.addAttribute("calDetail", new CalendarDetail());
        model.addAttribute("actionMode", "Add");
        model.addAttribute("user", userSession.getUser());
        return "sysadmin/calendarAMD";
    }

    @GetMapping(value = "/Calendar.action", params = "action=Modify")
    public String modifyForm(@RequestParam(defaultValue = "") String year, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        try (Connection conn = openConnection()) {
            CalendarSelector sel = new CalendarSelector();
            sel.setDbUsed(getDbUsed());
            calUtil.GetHolidayRows(conn, sel, year);
            CalendarDetail cd = sel.getCalendarrows().length > 0 ? sel.getCalendarrows()[0] : new CalendarDetail();
            model.addAttribute("calDetail", cd);
            model.addAttribute("actionMode", "Update");
            model.addAttribute("user", userSession.getUser());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "sysadmin/calendarAMD";
    }

    @PostMapping(value = "/Calendar.action", params = "action=Add")
    public String add(CalendarDetail calDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            calUtil.InsertUpdateHoli(conn, calDetail, getDbUsed(), userId, 1);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/calendarAMD";
        }
        return "redirect:/Calendar.action";
    }

    @PostMapping(value = "/Calendar.action", params = "action=Update")
    public String update(CalendarDetail calDetail, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String userId = userSession.getUser() != null ? userSession.getUser().getUserId() : "";
        try (Connection conn = openConnection()) {
            calUtil.InsertUpdateHoli(conn, calDetail, getDbUsed(), userId, 2);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sysadmin/calendarAMD";
        }
        return "redirect:/Calendar.action";
    }
}
