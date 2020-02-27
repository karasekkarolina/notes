package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.DraftNoteRepository
import cz.blackchameleon.notes.data.OpenNoteRepository
import cz.blackchameleon.notes.framework.model.Note

/**
 * Use case that sets locally stored draft note
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class SetDraftNote(
    private val draftNoteRepository: DraftNoteRepository
) {
    suspend operator fun invoke(note: Note?) = draftNoteRepository.setDraftNote(note)
}