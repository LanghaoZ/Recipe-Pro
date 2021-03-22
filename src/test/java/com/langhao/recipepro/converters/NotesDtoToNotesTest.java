package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Notes;
import com.langhao.recipepro.dto.NotesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesDtoToNotesTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesDtoToNotes converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesDtoToNotes();

    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new NotesDto()));
    }

    @Test
    public void convert() throws Exception {

        NotesDto notesCommand = new NotesDto();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}
