package com.shishkin.auctionapp.model;

import com.shishkin.auctionapp.validator.ValidImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    private String title;

    @NotBlank(message = "Category should not be empty")
    private String categoryTitle;

    private String description;

    @ValidImage
    private MultipartFile image;

    private String imagePath;

    @NotNull(message = "Price should not be empty")
    @Min(value = 0, message = "Price should be greater than 0")
    private Long price;
}
