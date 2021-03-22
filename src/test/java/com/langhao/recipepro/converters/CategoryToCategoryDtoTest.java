package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Category;
import com.langhao.recipepro.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryDtoTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "TEST";
    CategoryToCategoryDto converter;

    @BeforeEach
    public void setUp() {
        converter = new CategoryToCategoryDto();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvert() throws Exception {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryDto categoryDto = converter.convert(category);

        assertEquals(ID_VALUE, categoryDto.getId());
        assertEquals(DESCRIPTION, categoryDto.getDescription());
    }
}
