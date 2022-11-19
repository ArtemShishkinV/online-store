package com.shishkin.auctionapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    @Size(min = 5, max = 32, message = "Название категории должно быть длиной от 5 до 32 символов!")
    private String title;
}
