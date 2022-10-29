package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductToEntityMapper {
    @Mapping(target = "image", expression = "java(product.getImage().getOriginalFilename())")
    ProductEntity productToProductEntity(Product product);
    @Mapping(target = "categoryTitle", source = "category.title")
    @Mapping(target = "imagePath", expression = "java(productEntity.getImagePath())")
    @Mapping(target = "image", ignore = true)
    Product productEntityToProduct(ProductEntity productEntity);
}
