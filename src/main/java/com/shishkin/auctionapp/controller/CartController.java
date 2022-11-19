package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", cartService.findAll());
        return "/cart/index";
    }

    @PostMapping("/add")
    public String add(Product product) {
        cartService.add(product);
        return "redirect:/products";
    }

}
