package com.shishkin.auctionapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;

    @NotBlank(message = "Title should not be empty")
    @Size(min = 5, max = 32, message = "Title should be between 5 and 32 characters")
    private String title;
}
