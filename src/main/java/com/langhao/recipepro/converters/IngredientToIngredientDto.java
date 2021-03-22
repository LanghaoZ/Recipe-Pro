package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Ingredient;
import com.langhao.recipepro.dto.IngredientDto;
import com.sun.istack.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientDto implements Converter<Ingredient, IngredientDto> {

    private final UnitOfMeasureToUnitOfMeasureDto uomConverter;

    public IngredientToIngredientDto(UnitOfMeasureToUnitOfMeasureDto uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    @Nullable
    public synchronized IngredientDto convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        final IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(source.getId());
        ingredientDto.setAmount(source.getAmount());
        ingredientDto.setDescription(source.getDescription());
        ingredientDto.setUnitOfMeasure(uomConverter.convert(source.getUom()));

        return ingredientDto;
    }
}
