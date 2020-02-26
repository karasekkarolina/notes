package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository

/**
 * Use case that returns notes list
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class GetNotesList(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke() = notesRepository.getNotes()
}