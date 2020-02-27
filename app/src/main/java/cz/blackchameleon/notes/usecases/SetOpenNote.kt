package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.OpenNoteRepository
import cz.blackchameleon.notes.framework.model.Note

/**
 * Use case that sets locally stored opened note
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class SetOpenNote(
    private val openNoteRepository: OpenNoteRepository
) {
    suspend operator fun invoke(note: Note?) = openNoteRepository.setOpenNote(note)
}