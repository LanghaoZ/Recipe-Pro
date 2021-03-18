package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.UnitOfMeasure;
import com.langhao.recipepro.dto.UnitOfMeasureDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure> {

    @Override
    public synchronized UnitOfMeasure convert(UnitOfMeasureDto source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());

        return uom;
    }
}
