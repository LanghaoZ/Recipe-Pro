package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Category;
import com.langhao.recipepro.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDtoToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "TEST";
    CategoryDtoToCategory converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new CategoryDtoToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new CategoryDto()));
    }

    @Test
    public void testConvert() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID_VALUE);
        categoryDto.setDescription(DESCRIPTION);

        Category category = converter.convert(categoryDto);

        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}
