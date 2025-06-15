package org.uaf.CD_WEB_2025.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.CD_WEB_2025.entity.Discount;
import org.uaf.CD_WEB_2025.entity.Product;
import org.uaf.CD_WEB_2025.entity.User;
import org.uaf.CD_WEB_2025.services.DiscountServiceImp;
import org.uaf.CD_WEB_2025.services.ProductServiceImp;

import java.util.List;

@Controller
public class ListProduct {

    private final ProductServiceImp productServiceImp;
    private final DiscountServiceImp discountServiceImp;

    @Autowired
    public ListProduct(ProductServiceImp productServiceImp, DiscountServiceImp discountServiceImp) {
        this.productServiceImp = productServiceImp;
        this.discountServiceImp = discountServiceImp;
    }

    @RequestMapping(value = "/listProduct")
    public String showListProduct(Model model,
                                  HttpSession session,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "kind", required = false, defaultValue = "") String kind,
                                  @RequestParam(value = "sort", required = false, defaultValue = "discount") String sort,
                                  @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
                                  @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {

        User user = (User) session.getAttribute("auth");
        List<Product> loveList = null;
        Page<Product> pageResult;

        try {
            pageResult = productServiceImp.listAllPr(page, sort, sortDir, kind);
        } catch (Exception e) {
            e.printStackTrace();
            pageResult = Page.empty();
        }

        if (user != null) {
            loveList = productServiceImp.listLikeProduct(user.getIdUser());
        }

        List<Product> discountProducts = productServiceImp.getListPrDiscount();

        // Gửi dữ liệu ra view
        model.addAttribute("listPr", pageResult.getContent());
        model.addAttribute("listlike", loveList);
        model.addAttribute("listDiscount", discountProducts);
        model.addAttribute("pageCurrent", page);
        model.addAttribute("total", pageResult.getTotalPages());
        model.addAttribute("user", user);
        model.addAttribute("sort", sort);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("kind", kind);
        model.addAttribute("keyword", keyword);

        return "list_product";
    }
}
