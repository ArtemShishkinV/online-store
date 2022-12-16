package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.model.Category;
import com.shishkin.auctionapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/index";
    }

    @GetMapping(value = "/create")
    public String showCreate(@ModelAttribute("category") Category category) {
        return "category/create";
    }

    @PostMapping()
    public String create(@Valid @ModelAttribute("category") Category category,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/create";
        }
        categoryService.add(category);
        return "redirect:/categories";
    }


}
