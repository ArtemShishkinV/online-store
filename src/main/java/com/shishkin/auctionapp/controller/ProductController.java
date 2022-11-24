package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.service.CategoryService;
import com.shishkin.auctionapp.service.FileUploadService;
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
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private FileUploadService fileUploadService;

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
                         Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/create";
        }
        ProductEntity productEntity = productService.add(product);

        fileUploadService.uploadFile(Paths.get("products",
                String.valueOf(productEntity.getId())).toString(), product.getImage());

        return "redirect:/products";
    }


}
