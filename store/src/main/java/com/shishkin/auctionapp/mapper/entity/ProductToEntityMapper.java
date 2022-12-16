package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.model.Product;

//@Mapper(componentModel="spring")
public interface ProductToEntityMapper {
    //    @Mapping(target = "categoryId", ignore = true)
//    @Mapping(target = "image", expression = "java(product.getImage().getOriginalFilename())")
    ProductEntity productToProductEntity(Product product);

    //    @Mapping(target = "categoryTitle", ignore = true)
//    @Mapping(target = "imagePath", expression = "java(productEntity.getImagePath())")
//    @Mapping(target = "image", ignore = true)
    Product productEntityToProduct(ProductEntity productEntity);
}
