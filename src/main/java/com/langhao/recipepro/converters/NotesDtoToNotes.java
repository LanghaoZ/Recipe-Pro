package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Notes;
import com.langhao.recipepro.dto.NotesDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesDtoToNotes implements Converter<NotesDto, Notes> {

    @Override
    public synchronized Notes convert(NotesDto source) {
        if (source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
