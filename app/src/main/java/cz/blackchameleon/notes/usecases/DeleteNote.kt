package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository
import cz.blackchameleon.notes.framework.model.Note

/**
 * Use case that handles note deletion
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class DeleteNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.deleteNote(note.id)
}