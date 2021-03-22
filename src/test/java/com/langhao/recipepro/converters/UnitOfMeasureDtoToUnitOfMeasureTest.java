package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.UnitOfMeasure;
import com.langhao.recipepro.dto.UnitOfMeasureDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureDtoToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureDtoToUnitOfMeasure converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureDtoToUnitOfMeasure();

    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureDto()));
    }

    @Test
    public void convert() throws Exception {
        UnitOfMeasureDto dto = new UnitOfMeasureDto();
        dto.setId(LONG_VALUE);
        dto.setDescription(DESCRIPTION);

        UnitOfMeasure uom = converter.convert(dto);

        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}
