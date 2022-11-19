package com.shishkin.auctionapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;


@Table("category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @PrimaryKey
    private Long id = UUID.randomUUID().getMostSignificantBits();
    private String title;
}
