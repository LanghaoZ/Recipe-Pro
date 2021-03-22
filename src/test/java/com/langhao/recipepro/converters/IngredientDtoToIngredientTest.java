package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Ingredient;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.IngredientDto;
import com.langhao.recipepro.dto.UnitOfMeasureDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientDtoToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "TEST";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    IngredientDtoToIngredient converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientDtoToIngredient(new UnitOfMeasureDtoToUnitOfMeasure());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new IngredientDto()));
    }

    @Test
    public void convert() throws Exception {
        IngredientDto dto = new IngredientDto();
        dto.setId(ID_VALUE);
        dto.setAmount(AMOUNT);
        dto.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();
        unitOfMeasureDto.setId(UOM_ID);
        dto.setUnitOfMeasure(unitOfMeasureDto);

        Ingredient ingredient = converter.convert(dto);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        IngredientDto dto = new IngredientDto();
        dto.setId(ID_VALUE);
        dto.setAmount(AMOUNT);
        dto.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();

        Ingredient ingredient = converter.convert(dto);
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}
