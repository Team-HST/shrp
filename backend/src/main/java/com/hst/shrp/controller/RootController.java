package com.hst.shrp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dudgns0612@gmail.com
 */
@Controller
public class RootController implements ErrorController {
    public RootController() {
    }

    @GetMapping(value = "{path:[^\\\\.]*}")
    public String html5Forwarding() {
        return "forward:/index.html";
    }

    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
