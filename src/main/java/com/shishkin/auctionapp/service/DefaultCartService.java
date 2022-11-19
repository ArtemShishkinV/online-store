package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.entity.CartEntity;
import com.shishkin.auctionapp.entity.user.UserEntity;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.repository.CartRepository;
import com.shishkin.auctionapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DefaultCartService implements CartService {
    private ProductService productService;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(
                        cartRepository.findAllByUserId(getAuthentication().getId()).spliterator(),
                        false)
                .map(item -> productService.findById(item.getProductId()))
                .collect(Collectors.toList());
    }

    @Override
    public CartEntity add(Product product) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setProductId(product.getId());
        cartEntity.setUserId(getAuthentication().getId());
        return cartRepository.save(cartEntity);
    }

    private UserEntity getAuthentication() {
        return userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
