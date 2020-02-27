package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.DraftNoteRepository

/**
 * Use case that returns current draft note, null otherwise
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class GetDraftNote(
    private val draftNoteRepository: DraftNoteRepository
) {
    suspend operator fun invoke() = draftNoteRepository.getDraftNote()
}