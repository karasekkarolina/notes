package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.Note

/**
 * Receives notes from framework
 */
class OpenNoteRepository(
    private val source: OpenNoteSource
) {
    suspend fun getOpenNote(): Note? = source.getOpenNote()
    suspend fun setOpenNote(note: Note) = source.setOpenNote(note)
}