package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.model.Note

/**
 * Interface specifying possible actions with locally stored data source in framework layer
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
interface DraftNoteSource {
    suspend fun getDraftNote(): Note?
    suspend fun setDraftNote(note: Note?)
}