package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.services.ProductServiceImp;

import java.util.List;

import java.util.List;

@Controller
public class SearchProduct {

    private final ProductServiceImp productServiceImp;

    @Autowired
    public SearchProduct(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productServiceImp.searchAutocomplete(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "search_result"; // Tên trang .html hiển thị kết quả
    }
}