package com.siem.siem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/test")
    public String test() {
        return "Uspeh!!!!";
    }
}
