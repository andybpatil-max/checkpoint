package com.webfiche.checkpoint.inclear.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webfiche.checkpoint.inclear.beans.EarmarkSelector;
import com.webfiche.checkpoint.util.FileOutput;
import com.webfiche.checkpoint.util.FileUtil;

@Controller
public class ChexReleaseEarmarkController extends InclearBaseController {

    private ArrayList<String> filterReleased(ArrayList<String> fileList, String outDir) {
        ArrayList<String> filtered = new ArrayList<>();
        for (String f : fileList) {
            File released = new File(outDir + f.replaceAll(".dat", ".released"));
            if (!released.exists()) filtered.add(f);
        }
        return filtered;
    }

    @GetMapping("/ChexReleaseEarmark.action")
    public String list(Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String outDir = getLocOutputDir() + "incl/";
        EarmarkSelector emSel = new EarmarkSelector();
        emSel.setProcessDate(getApplDate());
        String[] fileList = FileUtil.GetEarmarkFiles(outDir);
        ArrayList<String> fileArray = new ArrayList<>();
        if (fileList != null && fileList.length > 0) {
            Collections.addAll(fileArray, fileList);
            fileArray = filterReleased(fileArray, outDir);
        }
        if (fileArray.isEmpty()) {
            emSel.setErrorVec("norelease", "info.nofiles2release");
        } else {
            emSel.setRelFile(fileArray);
        }
        model.addAttribute("sel", emSel);
        model.addAttribute("user", userSession.getUser());
        return "inclear/chexrelearmark";
    }

    @PostMapping(value = "/ChexReleaseEarmark.action", params = "action=Release")
    public String release(@RequestParam(defaultValue = "") String releaseFileName, Model model) {
        if (isNotLoggedIn()) return "redirect:/login";
        String outDir = getLocOutputDir() + "incl/";
        EarmarkSelector emSel = new EarmarkSelector();
        emSel.setProcessDate(getApplDate());
        if (!releaseFileName.isEmpty()) {
            try {
                String ackFile = releaseFileName.substring(0, releaseFileName.indexOf(".")) + ".ack";
                new FileOutput(outDir + ackFile).close();
                new FileOutput(outDir + releaseFileName.replaceAll(".dat", ".released")).close();
                emSel.setErrorVec(releaseFileName, "result.file.released");
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        String[] fileList = FileUtil.GetEarmarkFiles(outDir);
        ArrayList<String> fileArray = new ArrayList<>();
        if (fileList != null && fileList.length > 0) {
            Collections.addAll(fileArray, fileList);
            fileArray = filterReleased(fileArray, outDir);
        }
        if (fileArray.isEmpty()) {
            emSel.setErrorVec("norelease", "info.nofiles2release");
        } else {
            emSel.setRelFile(fileArray);
        }
        model.addAttribute("sel", emSel);
        model.addAttribute("user", userSession.getUser());
        return "inclear/chexrelearmark";
    }
}
