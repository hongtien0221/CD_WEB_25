package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.services.ProductServiceImp;
import org.uaf.cd_web.dto.ProductSuggestionDTO;

import java.util.List;

import java.util.List;
import java.util.Map;

@Controller
public class SearchProduct {

    private final ProductServiceImp productServiceImp;

    @Autowired
    public SearchProduct(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productServiceImp.searchByKeyword(keyword); // method này trả List<Product>
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "search_result";
    }



    @PostMapping("/api/search")
    @ResponseBody
    public List<ProductSuggestionDTO> apiSearch(@RequestBody Map<String, String> payload) {
        String keyword = payload.get("query");
        return productServiceImp.searchAutocomplete(keyword);
    }
    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable("id") String id, Model model) {
        Product product = productServiceImp.getProductById(id);
        if (product == null) {
            return "error-404"; // nếu không có sản phẩm thì trả về trang lỗi
        }
        model.addAttribute("product", product);
        return "single_product"; // đây là tên file HTML bạn cần tạo
    }


}