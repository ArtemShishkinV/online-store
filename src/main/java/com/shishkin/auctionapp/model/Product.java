package com.shishkin.auctionapp.model;

import com.shishkin.auctionapp.validator.ValidImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    @NotEmpty(message = "Название продукта не может быть пустым!")
    @Size(min = 2, max = 30, message = "Название должно быть от 2 до 30 символов")
    private String title;

    @NotBlank(message = "Категория не может быть пустой")
    private String categoryTitle;

    private String description;

    @ValidImage
    private MultipartFile image;

    private String imagePath;

    @NotNull(message = "Цена не может быть пустой!")
    @Min(value = 0, message = "Цена должна быть больше 0!")
    private Long price;
}
