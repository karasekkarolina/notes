package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository
import cz.blackchameleon.notes.framework.model.Note

/**
 * Use case that handles note creation
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class CreateNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.createNote(note)
}