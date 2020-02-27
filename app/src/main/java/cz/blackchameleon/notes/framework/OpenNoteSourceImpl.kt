package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.data.OpenNoteSource
import cz.blackchameleon.notes.framework.model.Note

/**
 * Implementation of [OpenNoteSource] specifying how to provide data
 *
 * @see OpenNoteSource
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class OpenNoteSourceImpl : OpenNoteSource {
    // Locally stored value referring to currently opened note
    private var openNote: Note? = null

    override suspend fun getOpenNote(): Note? = openNote

    override suspend fun setOpenNote(note: Note?) {
        openNote = note
    }
}