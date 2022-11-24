package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.service.CategoryService;
import com.shishkin.auctionapp.service.DefaultProductService;
import com.shishkin.auctionapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private DefaultProductService productService;
    private CategoryService categoryService;

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable(required = false) Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/show";
    }


    @GetMapping("/create")
    public String showCreate(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "product/create";
    }


    @PostMapping()
    public String create(@Valid @ModelAttribute("product") Product product,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/create";
        }
        productService.add(product);

        return "redirect:/products";
    }

    @PostMapping("/create/many")
    public String generate() {
        this.productService.saveAll(this.productService.generate(10));
        return "redirect:/products";
    }

}
