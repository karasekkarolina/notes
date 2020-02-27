package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.model.Note

/**
 * Uses [OpenNoteSource] implementation for providing request responses
 *
 * @param source
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class OpenNoteRepository(
    private val source: OpenNoteSource
) {
    suspend fun getOpenNote(): Note? = source.getOpenNote()
    suspend fun setOpenNote(note: Note) = source.setOpenNote(note)
}