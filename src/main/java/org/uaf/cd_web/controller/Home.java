package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.CartServiceImp;
import org.uaf.cd_web.services.UserServiceImp;

@Controller
public class Home {
    private UserServiceImp userServiceImp;
    private final CartServiceImp cartServiceImp;

    @Autowired
    public Home(UserServiceImp userServiceImp, CartServiceImp cartServiceImp) {
        this.userServiceImp = userServiceImp;
        this.cartServiceImp = cartServiceImp;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            // Không tạo user giả nữa! Chỉ gán thông báo hoặc hiển thị dạng khách
            model.addAttribute("isGuest", true);
        } else {
            model.addAttribute("isGuest", false);
            model.addAttribute("user", user);
        }

        return "index";
    }

}
