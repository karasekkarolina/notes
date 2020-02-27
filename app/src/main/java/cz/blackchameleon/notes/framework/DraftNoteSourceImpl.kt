package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.data.DraftNoteSource
import cz.blackchameleon.notes.framework.model.Note

/**
 * Implementation of [DraftNoteSource] specifying how to provide data
 *
 * @see DraftNoteSource
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class DraftNoteSourceImpl : DraftNoteSource {
    // Locally stored value referring to currently opened note
    private var draftNote: Note? = null

    override suspend fun getDraftNote(): Note? = draftNote

    override suspend fun setDraftNote(note: Note?) {
        draftNote = note
    }
}