package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Notes;
import com.langhao.recipepro.dto.NotesDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesDto implements Converter<Notes, NotesDto> {

    @Override
    public synchronized NotesDto convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesDto notesDto = new NotesDto();
        notesDto.setId(source.getId());
        notesDto.setRecipeNotes(source.getRecipeNotes());

        return notesDto;
    }
}
