package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOut {

    @GetMapping("/logOut")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
