package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.model.Note

/**
 * Uses [DraftNoteSource] implementation for providing request responses
 *
 * @param source
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class DraftNoteRepository(
    private val source: DraftNoteSource
) {
    suspend fun getDraftNote(): Note? = source.getDraftNote()
    suspend fun setDraftNote(note: Note?) = source.setDraftNote(note)
}