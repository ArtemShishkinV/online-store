package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.model.Category;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.service.CategoryService;
import com.shishkin.auctionapp.service.ProductService;
import com.shishkin.auctionapp.validator.CategoryValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "api/store", produces = "application/json")
@CrossOrigin(origins = "https://store:8080")
@AllArgsConstructor
public class StoreController {
    private ProductService productService;

    private CategoryService categoryService;

    @GetMapping(path = "/products", params = "maxprice")
    public List<Product> getProductsWithMaxPrice() {
        return productService.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Product::getPrice))
                .limit(5).toList();
    }

    @PostMapping(path = "/category/add", consumes = "application/json")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        if (!CategoryValidator.isValidTitle(category)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid json or title category");
        }
        categoryService.add(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK!");
    }
}
