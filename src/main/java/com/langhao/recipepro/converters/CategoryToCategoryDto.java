package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Category;
import com.langhao.recipepro.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {

    @Override
    public synchronized CategoryDto convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(source.getId());
        categoryDto.setDescription(source.getDescription());

        return categoryDto;
    }
}
