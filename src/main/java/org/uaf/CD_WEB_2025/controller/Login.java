package org.uaf.CD_WEB_2025.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.CD_WEB_2025.components.Encryption;
import org.uaf.CD_WEB_2025.components.Powers;
import org.uaf.CD_WEB_2025.entity.User;
import org.uaf.CD_WEB_2025.services.UserServiceImp;

@Controller
public class Login {

    @Autowired
    private UserServiceImp userService;

    // Trang hiển thị form login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam("username") String username,
                        @RequestParam("passw") String passw,
                        RedirectAttributes redirectAttributes) {

        User user = userService.checkLogin(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "redirect:/login";
        }

        if (user.getDecentralization() == -1) {
            redirectAttributes.addFlashAttribute("error", "Tài khoản đã bị khóa");
            return "redirect:/login";
        }

        if (passw == null || passw.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng nhập mật khẩu");
            return "redirect:/login";
        }

        String hashedInput = Encryption.toSHA1(passw);
        if (!hashedInput.equals(user.getPassw())) {
            redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "redirect:/login";
        }

        // Đăng nhập thành công
        session.setAttribute("auth", user);
        session.setAttribute("idUser", user.getIdUser());

        if (user.getDecentralization() == Powers.ADMIN) {
            return "redirect:/admin/main";
        } else if (user.getDecentralization() == Powers.MANAGER) {
            return "redirect:/admin/productManager?page=1";
        } else {
            return "redirect:/";
        }
    }
}
