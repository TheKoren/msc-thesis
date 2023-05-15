package com.koren.digitaltwin.controllers;

import com.koren.digitaltwin.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static com.koren.digitaltwin.Constants.APP_NAME;

@Controller
public class BasicController {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final DataService dataService = new DataService();

    private int n = 0;

    // GET /api/name
    @ResponseBody
    @GetMapping("/api/name")
    public String apiName() {
        return APP_NAME;
    }

    // GET /api/time
    @ResponseBody
    @GetMapping("/api/time")
    public String apiTime() {
        return dateFormat.format(System.currentTimeMillis());
    }

    // GET /api/n
    @ResponseBody
    @GetMapping("/api/n")
    public int apiN() {
        ++n;
        return n;
    }
    // GET /
    @GetMapping("/")
    public String index(Model model) throws IOException {
        ++n;
        model.addAttribute("appName", APP_NAME);
        model.addAttribute("time", dateFormat.format(System.currentTimeMillis()));
        model.addAttribute("data", Arrays.toString(dataService.readCsvFile("data.csv").toArray()));
        return "index";
    }
}
