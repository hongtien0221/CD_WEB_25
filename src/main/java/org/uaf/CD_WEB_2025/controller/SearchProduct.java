package org.uaf.CD_WEB_2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.uaf.CD_WEB_2025.entity.Product;
import org.uaf.CD_WEB_2025.services.ProductServiceImp;

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
    @ResponseBody
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
        return productServiceImp.searchAutocomplete(keyword);
    }


}