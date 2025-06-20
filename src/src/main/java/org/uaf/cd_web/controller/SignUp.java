package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.components.Encryption;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignUp {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(HttpSession session, Model model,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("phone") String phone,
                         @RequestParam("passw") String passw,
                         @RequestParam("repassw") String repassw,
                         RedirectAttributes redirectAttributes) {

        boolean exist = userService.checkUserExit(email, phone);
        if (exist) {
            redirectAttributes.addFlashAttribute("errorAcc", "* Tài khoản đã được sử dụng");
            return "redirect:/signup";
        }

        if (!passw.equals(repassw)) {
            redirectAttributes.addFlashAttribute("errorDup", "* Mật khẩu không trùng khớp");
            return "redirect:/signup";
        }

        String encryptedPassword = Encryption.toSHA1(passw);
        userService.createUser(name, phone, email, encryptedPassword);

        // 🔥 Lấy lại user từ DB và lưu vào session
        User user = userService.getUserByEmail(email);
        session.setAttribute("auth", user);

        return "redirect:/";
    }

}
