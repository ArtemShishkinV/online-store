package com.shishkin.auctionapp.entity;

import com.shishkin.auctionapp.entity.udt.CategoryUdt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;


@Table("product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @PrimaryKey
    private Long id = UUID.randomUUID().getMostSignificantBits();
    private String title;
    private String image;
    private String description;
    private Long price;

    @Column("category")
    private CategoryUdt category;

    public String getImagePath() {
        if (image == null || id == null) return null;
        return "/images/uploads/products/" + id + "/" + image;
    }
}
