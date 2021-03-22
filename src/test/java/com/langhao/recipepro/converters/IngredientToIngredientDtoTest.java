package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Ingredient;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.domain.UnitOfMeasure;
import com.langhao.recipepro.dto.IngredientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientToIngredientDtoTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long UOM_ID = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);

    IngredientToIngredientDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvert() throws Exception {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientDto ingredientDto = converter.convert(ingredient);
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertNotNull(ingredientDto.getUom());
        assertEquals(UOM_ID, ingredientDto.getUom().getId());
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

    @Test
    public void testConvertNullUOM() throws Exception {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);

        IngredientDto ingredientDto = converter.convert(ingredient);

        assertNull(ingredientDto.getUom());
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }
}
