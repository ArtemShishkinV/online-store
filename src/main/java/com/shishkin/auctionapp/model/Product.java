package com.shishkin.auctionapp.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Product {
    Long id;
    String title;
    String categoryTitle;
    String image;
    BigDecimal price;
}
