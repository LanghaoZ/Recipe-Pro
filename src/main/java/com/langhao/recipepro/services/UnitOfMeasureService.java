package com.langhao.recipepro.services;

import com.langhao.recipepro.dto.UnitOfMeasureDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UnitOfMeasureService {
    Set<UnitOfMeasureDto> listAllUoms();
}
