package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Category;
import com.langhao.recipepro.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category> {

    @Override
    public synchronized Category convert(CategoryDto source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }

}
