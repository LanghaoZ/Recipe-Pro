package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.UnitOfMeasure;
import com.langhao.recipepro.dto.UnitOfMeasureDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureDto implements Converter<UnitOfMeasure, UnitOfMeasureDto> {

    @Override
    public synchronized UnitOfMeasureDto convert(UnitOfMeasure source) {
        if (source != null) {
            final UnitOfMeasureDto uomd = new UnitOfMeasureDto();
            uomd.setId(source.getId());
            uomd.setDescription(source.getDescription());

            return uomd;
        }

        return null;
    }
}
