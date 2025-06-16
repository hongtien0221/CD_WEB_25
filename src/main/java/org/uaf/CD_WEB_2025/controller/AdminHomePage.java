package org.uaf.CD_WEB_2025.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.CD_WEB_2025.components.Powers;
import org.uaf.CD_WEB_2025.entity.Contact;
import org.uaf.CD_WEB_2025.entity.Product;
import org.uaf.CD_WEB_2025.entity.User;
import org.uaf.CD_WEB_2025.services.ContactServiceImp;
import org.uaf.CD_WEB_2025.services.OrderServiceImp;
import org.uaf.CD_WEB_2025.services.ProductServiceImp;
import org.uaf.CD_WEB_2025.services.UserServiceImp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHomePage {

    private final ProductServiceImp productServiceImp;
    private final OrderServiceImp orderServiceImp;
    private final ContactServiceImp contactServiceImp;
    private final UserServiceImp userServiceImp;

    @Autowired
    public AdminHomePage(ProductServiceImp productServiceImp,
                         OrderServiceImp orderServiceImp,
                         ContactServiceImp contactServiceImp,
                         UserServiceImp userServiceImp) {
        this.productServiceImp = productServiceImp;
        this.orderServiceImp = orderServiceImp;
        this.contactServiceImp = contactServiceImp;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/main")
    public String homeAdmin(Model model, HttpSession session) {
        User user = (User) session.getAttribute("auth");

        // Kiểm tra quyền truy cập admin
        if (user == null || user.getDecentralization() != Powers.ADMIN) {
            return "redirect:/login";
        }

        int newbie = userServiceImp.getNewbie().size();
        int tur0 = 0, tur = 0, tur1 = 0;
        StringBuilder data0 = new StringBuilder();
        StringBuilder data = new StringBuilder();
        StringBuilder data1 = new StringBuilder();

        for (int i = 1; i <= 12; i++) {
            int t0 = orderServiceImp.getTurnover(i, 2021);
            int t = orderServiceImp.getTurnover(i, 2022);
            int t1 = orderServiceImp.getTurnover(i, 2023);

            tur0 += t0;
            tur += t;
            tur1 += t1;

            data0.append(t0).append(i < 12 ? "," : "");
            data.append(t).append(i < 12 ? "," : "");
            data1.append(t1).append(i < 12 ? "," : "");
        }

        int nowTur = orderServiceImp.getTurnover(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        int allTur = orderServiceImp.getAllTurnover();
        int saledPR = orderServiceImp.getSalerPR();
        int saledAll = orderServiceImp.getSalerPRAll();
        String stopSaledPR = String.valueOf(productServiceImp.getStopPr());
        List<Product> pr = productServiceImp.getListProductHostSale();
        List<Contact> listContact = contactServiceImp.getListContact();
        Collections.sort(listContact, Comparator.comparingInt(Contact::getCondition).reversed());

        model.addAttribute("stopSaled", stopSaledPR);
        model.addAttribute("saledPr", saledPR);
        model.addAttribute("saledPrAll", saledAll);
        model.addAttribute("newbie", newbie);
        model.addAttribute("nowTur", nowTur);
        model.addAttribute("tur", tur);
        model.addAttribute("tur1", tur1);
        model.addAttribute("allTur", allTur);
        model.addAttribute("data0", data0.toString());
        model.addAttribute("data", data.toString());
        model.addAttribute("data1", data1.toString());
        model.addAttribute("hotSale", pr);
        model.addAttribute("listContact", listContact);
        model.addAttribute("sumContact", contactServiceImp.getSumContact());

        return "admin_home";
    }

    @GetMapping("/ViewContact")
    public String viewContact(@RequestParam("idContact") String idContact,
                              @RequestParam("iduser") String iduser,
                              @RequestParam("nameUser") String nameUser,
                              @RequestParam("idcustomer") String idcustomer,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone,
                              @RequestParam("content") String content,
                              @RequestParam("condition") String condition,
                              Model model) {
        model.addAttribute("idContact", idContact);
        model.addAttribute("iduser", iduser);
        model.addAttribute("idcustomer", idcustomer);
        model.addAttribute("nameUser", nameUser);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("content", content);
        model.addAttribute("condition", condition);
        return "viewContact";
    }
}
