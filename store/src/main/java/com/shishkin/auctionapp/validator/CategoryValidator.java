package com.shishkin.auctionapp.validator;

import com.shishkin.auctionapp.model.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryValidator {
    public static boolean isValidTitle(Category category) {
        return category.getTitle() != null && !category.getTitle().isBlank() && category.getTitle().length() > 5;
    }
}
